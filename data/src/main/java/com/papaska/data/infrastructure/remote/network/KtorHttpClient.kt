package com.papaska.data.infrastructure.remote.network

import com.papaska.domain.http.ApiResponse
import com.papaska.domain.http.DomainHttpHeaders
import com.papaska.domain.http.DomainHttpMethod
import com.papaska.domain.http.DomainHttpParams
import com.papaska.domain.http.DomainHttpStatusCode
import com.papaska.domain.http.DomainUrlProtocol
import com.papaska.domain.http.isSuccess
import com.papaska.domain.repositories.local.TokenRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.onDownload
import io.ktor.client.plugins.onUpload
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.util.StringValues
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import java.io.IOException
import java.net.UnknownHostException
import io.ktor.http.Headers as KtorHeaders
import io.ktor.http.HttpMethod as KtorHttpMethod
import io.ktor.http.HttpStatusCode as KtorHttpStatusCode
import io.ktor.http.URLProtocol as KtorUrlProtocol

internal class KtorHttpClient(
    private val client: HttpClient,
    private val tokenRepository: TokenRepository,
) {

    /**
     * Выполняет HTTP-запрос к указанному URL и возвращает результат в виде [ApiResponse].
     *
     * Этот метод абстрагирует детали выполнения HTTP-запросов, включая:
     *  - Сериализацию и десериализацию данных
     *  - Обработку ошибок (сетевые ошибки, ошибки сервера, ошибки сериализации)
     *  - Установку заголовков, включая токен авторизации
     *  - Логирование запросов и ответов
     *
     * @param path [String] Путь к запрашиваемой конечной точке API.
     * @param method [DomainHttpMethod] HTTP-метод, который будет использоваться для запроса. Значение
     * по умолчанию - [DomainHttpMethod.Get].
     * @param protocol [URLProtocol] Протокол, который будет использоваться для подключения по URL.
     * По умолчанию берется из [BuildConfig.PROTOCOL].*
     * @param host [String] Хост сервера API. Значение по умолчанию получено из [BuildConfig.HOST].
     * @param port [Int] Номер порта, который будет использоваться для подключения по URL.
     * По умолчанию берется из [BuildConfig.PORT]. Если нужно использовать обычный [URLProtocol.HTTP] или
     * [URLProtocol.HTTPS], то порт ставить 80 443 соответсвенно
     * @param params [StringValues] Определяет параметры, которые будут добавлены к URL-адресу. Значением по
     * умолчанию является пустая строка значений.
     * @param body Тело запроса в формате JSON. Принимаемый тип - @Serializable data class.
     * Значение по умолчанию - null.
     *
     * @return [ApiResponse.Success] или [ApiResponse.Error] в завсисимости от статуса запроса.
     * Ответ [ApiResponse] содержит статус HTTP, заголовки и обработанные данные ответа
     *
     * @throws SerializationException В случае ошибки сериализации/десериализации данных.
     * @throws ServerResponseException В случае ошибки сервера.
     * @throws UnknownHostException В случае ошибки разрешения имени хоста.
     * @throws IOException В случае сетевых ошибок.
     */
    suspend operator fun <SuccessResponse, ErrorResponse> invoke(
        errorResponse: KSerializer<ErrorResponse>,
        successResponse: KSerializer<SuccessResponse>,
        host: String,
        path: String,
        port: Int,
        method: DomainHttpMethod,
        protocol: DomainUrlProtocol,
        body: Any?,
        params: DomainHttpParams,
        headers: DomainHttpHeaders,
        onDownload: (bytesSentTotal: Long, contentLength: Long?) -> Unit,
        onUpload: (bytesSentTotal: Long, contentLength: Long?) -> Unit,
    ): ApiResponse<out SuccessResponse, out ErrorResponse> = try {

        val response = client.request {
            this.method = method.toKtorHttpMethod()
            url {
                this.protocol = protocol.toKtorUrlProtocol()
                this.host = host
                this.port = port
                path(path)
                parameters.appendAll(params.toKtorStringValues())
            }
            contentType(ContentType.Application.Json)

            if (headers.containsKey("Authorization").not()) {
                header("Authorization", tokenRepository.get())
            }

            headers.entries.forEach {
                header(it.key, it.value)
            }

            body?.let {
                setBody(body)
            }

            onDownload { bytesSentTotal, contentLength -> onDownload(bytesSentTotal, contentLength) }
            onUpload { bytesSentTotal, contentLength -> onUpload(bytesSentTotal, contentLength) }
        }

        val resBody = response.bodyAsText()
        val domainHeaders = response.headers.toDomainHttpHeaders()
        val domainStatus = response.status.toDomainHttpStatus()

        if (domainStatus.isSuccess()) {

            val res = json.decodeFromString(successResponse, resBody)
            ApiResponse.Success(
                status = domainStatus,
                headers = domainHeaders,
                body = res,
            )
        } else {

            val res = json.decodeFromString(errorResponse, resBody)
            ApiResponse.Error(
                status = domainStatus,
                headers = domainHeaders,
                body = res,
            )
        }
    } catch (e: SerializationException) {

        ApiResponse.Error(
            status = DomainHttpStatusCode.SerializationException,
        )
    } catch (e: ServerResponseException) {

        ApiResponse.Error(
            status = DomainHttpStatusCode.InternalServerError,
        )
    } catch (e: UnknownHostException) {

        ApiResponse.Error(
            status = DomainHttpStatusCode.UnknownHostException,
        )
    } catch (e: IOException) {

        ApiResponse.Error(
            status = DomainHttpStatusCode.IOException,
        )
    } finally {
        client.close()
    }

    /**
     * Преобразует [DomainHttpMethod] из доменного слоя в [KtorHttpMethod].
     */
    private fun DomainHttpMethod.toKtorHttpMethod(): KtorHttpMethod {
        return when (this) {
            DomainHttpMethod.GET -> KtorHttpMethod.Get
            DomainHttpMethod.POST -> KtorHttpMethod.Post
            DomainHttpMethod.PUT -> KtorHttpMethod.Put
            DomainHttpMethod.DELETE -> KtorHttpMethod.Delete
            DomainHttpMethod.PATCH -> KtorHttpMethod.Patch
            DomainHttpMethod.HEAD -> KtorHttpMethod.Head
            DomainHttpMethod.OPTIONS -> KtorHttpMethod.Options
        }
    }

    /**
     * Преобразует из [KtorHttpStatusCode] в [DomainHttpStatusCode].
     */
    private fun KtorHttpStatusCode.toDomainHttpStatus(): DomainHttpStatusCode {
        return DomainHttpStatusCode(
            value = value,
            description = description,
        )
    }

    /**
     * Преобразует [KtorHeaders] в [DomainHttpHeaders].
     */
    private fun KtorHeaders.toDomainHttpHeaders(): DomainHttpHeaders {
        return this.entries().associate { (key, values) ->
            key to values
        }
    }

    /**
     * Преобразует [DomainUrlProtocol] из доменного слоя в [KtorUrlProtocol].
     */
    private fun DomainUrlProtocol.toKtorUrlProtocol(): KtorUrlProtocol {
        return when (this) {
            DomainUrlProtocol.HTTP -> URLProtocol.HTTP
            DomainUrlProtocol.HTTPS -> URLProtocol.HTTPS
        }
    }

    /**
     * Преобразует [DomainHttpParams] из доменного слоя в [StringValues].
     */
    private fun DomainHttpParams.toKtorStringValues(): StringValues {
        return StringValues.build {
            this@toKtorStringValues.entries.forEach { (key, value) ->
                append(key, value)
            }
        }
    }
}
