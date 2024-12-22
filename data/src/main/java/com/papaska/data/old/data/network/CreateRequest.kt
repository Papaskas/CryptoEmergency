package com.papaska.data.old.data.network

import android.content.Context
import android.util.Log
import com.papaska.data.infrastructure.local.datastore.DataStore
import com.papaska.data.infrastructure.local.datastore.keys.KeyImpl
import com.papaska.domain.http.ApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.http.path
import io.ktor.util.StringValues
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import okio.IOException
import java.net.UnknownHostException

/**
 * Выполняет HTTP-запрос к указанному URL и возвращает результат в виде [ApiResponse].
 *
 * Этот метод абстрагирует детали выполнения HTTP-запросов, включая:
 *  - Сериализацию и десериализацию данных
 *  - Обработку ошибок (сетевые ошибки, ошибки сервера, ошибки сериализации)
 *  - Установку заголовков, включая токен авторизации
 *  - Логирование запросов и ответов
 *
 * @param context [Context] Контекст, необходимый для извлечения токена из внутреннего хранилища.
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
@Throws(
    SerializationException::class,
    ServerResponseException::class,
    UnknownHostException::class,
    IOException::class,
)
suspend inline fun <reified SuccessResponse, reified ErrorResponse> HttpClient.createRequest(
    context: Context,
    path: String,
    method: HttpMethod,
    protocol: URLProtocol = URLProtocol.byName[BuildConfig.PROTOCOL]!!,
    host: String = BuildConfig.HOST,
    port: Int = BuildConfig.PORT,
    params: StringValues = StringValues.Empty,
    body: @Serializable Any? = null,
    overrideToken: String? = null,
): ApiResponse<out SuccessResponse, out ErrorResponse> = try {
    val response =
        request {
            this.method = method
            url {
                this.protocol = protocol
                this.host = host
                this.port = port
                path(path)
                parameters.appendAll(params)
            }
            contentType(ContentType.Application.Json)
            setHeaders(overrideToken, context)
            body(body)
        }

    val responseBody = response.body<String>()

    if (response.status.isSuccess()) {
        ApiResponse.Success(
            status = response.status,
            headers = response.headers,
            body = json.decodeFromString<SuccessResponse>(responseBody),
        )
    } else {
        ApiResponse.Error(
            status = response.status,
            headers = response.headers,
            body = json.decodeFromString<ErrorResponse>(responseBody),
        )
    }
} catch (e: SerializationException) { // Ошибка сериализации данных
    ErrorResponse::class.qualifiedName?.let { Log.e("SerializationException", it) }

    ApiResponse.Error(
        status = HttpStatusCode.SerializationException,
    )
} catch (e: ServerResponseException) { // 500 ошибка сервера
    ErrorResponse::class.qualifiedName?.let { Log.e("ServerResponseException", it) }

    ApiResponse.Error(
        status = HttpStatusCode.InternalServerError,
    )
} catch (e: UnknownHostException) { // Нет интернета
    ErrorResponse::class.qualifiedName?.let { Log.e("UnknownHostException", it) }

    ApiResponse.Error(
        status = HttpStatusCode.UnknownHostException,
    )
} catch (e: IOException) { // Ошибки соединений
    ErrorResponse::class.qualifiedName?.let { Log.e("IOException", it) }

    ApiResponse.Error(
        status = HttpStatusCode.IOException,
    )
}

suspend fun HttpRequestBuilder.setHeaders(
    overrideToken: String?,
    context: Context,
) {
    overrideToken?.let {
        header("Authorization", overrideToken)
    } ?: run {
        header("Authorization", DataStore(KeyImpl.TOKEN, context).get())
    }
}

fun HttpRequestBuilder.body(body: Any?) {
    if(body == null) return

    setBody(body)
}
