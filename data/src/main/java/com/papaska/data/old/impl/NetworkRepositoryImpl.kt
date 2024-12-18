package com.papaska.data.old.impl

import android.content.Context
import com.papaska.data.old.data.network.json
import com.papaska.domain.useCases.remote.ApiResponse
import com.papaska.domain.useCases.remote.HttpHeaders
import com.papaska.domain.useCases.remote.HttpMethod
import com.papaska.domain.useCases.remote.HttpParams
import com.papaska.domain.useCases.remote.HttpStatusCode
import com.papaska.domain.useCases.remote.NetworkRepository
import com.papaska.domain.useCases.remote.UrlProtocol
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.onDownload
import io.ktor.client.plugins.onUpload
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.http.path
import io.ktor.util.StringValues
import kotlinx.serialization.SerializationException
import java.io.IOException
import java.net.UnknownHostException
import io.ktor.http.Headers as KtorHeaders
import io.ktor.http.HttpMethod as KtorHttpMethod
import io.ktor.http.HttpStatusCode as KtorHttpStatusCode
import io.ktor.http.URLProtocol as KtorUrlProtocol

class NetworkRepositoryImpl(
    private val client: HttpClient,
    //private val tokenStore: TokenStore,
    private val context: Context,
) : NetworkRepository {

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
     * @param method [HttpMethod] HTTP-метод, который будет использоваться для запроса. Значение
     * по умолчанию - [HttpMethod.Get].
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
     * @param overrideToken [String] Токен, который должен быть включен в заголовок запроса. Если значение равно null,
     * функция извлечет токен из контекста.
     *
     * @return [ApiResponse.Success] или [ApiResponse.Error] в завсисимости от статуса запроса.
     * Ответ [ApiResponse] содержит статус HTTP, заголовки и обработанные данные ответа
     *
     * @throws SerializationException В случае ошибки сериализации/десериализации данных.
     * @throws ServerResponseException В случае ошибки сервера.
     * @throws UnknownHostException В случае ошибки разрешения имени хоста.
     * @throws IOException В случае сетевых ошибок.
     */
    override suspend fun <SuccessResponse, ErrorResponse> invoke(
        host: String,
        path: String,
        port: Int,
        method: HttpMethod,
        protocol: UrlProtocol,
        params: HttpParams,
        body: Any?,
        overrideToken: String?,
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
            setHeaders(overrideToken)
            body(body)

            onDownload { bytesSentTotal, contentLength -> onDownload(bytesSentTotal, contentLength) }
            onUpload { bytesSentTotal, contentLength -> onUpload(bytesSentTotal, contentLength) }
        }

        val responseBody = response.body<String>()
        val status = response.status.toDomainHttpStatus()
        val headers = response.headers.toDomainHttpHeaders()

        if (response.status.isSuccess()) {

            val res = json.decodeFromString<SuccessResponse>(responseBody)
            ApiResponse.Success(
                status = status,
                headers = headers,
                body = res,
            )
        } else {

            val errorResponse = json.decodeFromString<ErrorResponse>(responseBody)
            ApiResponse.Error(
                status = status,
                headers = headers,
                body = errorResponse,
            )
        }
    } catch (e: SerializationException) {

        ApiResponse.Error(
            status = HttpStatusCode.SerializationException,
        )
    } catch (e: ServerResponseException) {

        ApiResponse.Error(
            status = HttpStatusCode.InternalServerError,
        )
    } catch (e: UnknownHostException) {

        ApiResponse.Error(
            status = HttpStatusCode.UnknownHostException,
        )
    } catch (e: IOException) {

        ApiResponse.Error(
            status = HttpStatusCode.IOException,
        )
    } finally {
        client.close()
    }

    /**
     * Преобразует [HttpMethod] из доменного слоя в [KtorHttpMethod].
     */
    private fun HttpMethod.toKtorHttpMethod(): KtorHttpMethod {
        return when (this) {
            HttpMethod.GET -> KtorHttpMethod.Get
            HttpMethod.POST -> KtorHttpMethod.Post
            HttpMethod.PUT -> KtorHttpMethod.Put
            HttpMethod.DELETE -> KtorHttpMethod.Delete
            HttpMethod.PATCH -> KtorHttpMethod.Patch
        }
    }

    /**
     * Преобразует [UrlProtocol] из доменного слоя в [KtorUrlProtocol].
     */
    private fun UrlProtocol.toKtorUrlProtocol(): KtorUrlProtocol {
        return when (this) {
            UrlProtocol.HTTP -> KtorUrlProtocol.HTTP
            UrlProtocol.HTTPS -> KtorUrlProtocol.HTTPS
        }
    }

    /**
     * Преобразует [HttpParams] из доменного слоя в [StringValues].
     */
    private fun HttpParams.toKtorStringValues(): StringValues {
        return StringValues.build {
            this@toKtorStringValues.getAll().forEach { (key, value) ->
                append(key, value)
            }
        }
    }

    /**
     * Преобразует [KtorHttpStatusCode] из Ktor в [HttpStatusCode].
     */
    private fun KtorHttpStatusCode.toDomainHttpStatus(): HttpStatusCode {
        return HttpStatusCode(
            value = value,
            description = description,
        )
    }

    /**
     * Преобразует [KtorHeaders] из Ktor в [HttpHeaders].
     */
    private fun KtorHeaders.toDomainHttpHeaders(): HttpHeaders {
        return HttpHeaders(
            headers = entries().associate { (key, values) ->
                key to values
            },
        )
    }

    private suspend fun HttpRequestBuilder.setHeaders(
        overrideToken: String?,
    ) {
        overrideToken?.let {
            header("Authorization", overrideToken)
        } ?: run {
            //header("Authorization", DataStore(Keys.TOKEN, context).get())
        }
    }

    private fun HttpRequestBuilder.body(body: Any?) {
        if (body == null) return
        setBody(body)
    }
}
