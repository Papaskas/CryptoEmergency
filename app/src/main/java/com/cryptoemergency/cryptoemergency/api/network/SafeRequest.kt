package com.cryptoemergency.cryptoemergency.api.network

import android.content.Context
import com.cryptoemergency.cryptoemergency.BuildConfig
import com.cryptoemergency.cryptoemergency.api.store.Store
import com.cryptoemergency.cryptoemergency.repository.store.Keys
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
import kotlin.jvm.Throws

/**
 * Безопасная и универсальная функция HTTP-запроса, которая обрабатывает различные сценарии,
 * такие как исключения при сериализации, исключения при ответе сервера и проблемы с сетевым подключением.
 *
 * @param path Путь к запрашиваемой конечной точке API.
 * @param context Контекст, необходимый для извлечения токена из внутреннего хранилища.
 * @param host Хост сервера API. Значение по умолчанию получено из BuildConfig.HOST.
 * @param protocol Протокол, который будет использоваться для подключения по URL. По умолчанию берется из BuildConfig.
 * @param port Номер порта, который будет использоваться для подключения по URL. По умолчанию берется из BuildConfig.
 * Если нужно использовать обычный Http Https, то порт ставить 80 443 соответсвенно
 * @param params Определяет параметры, которые будут добавлены к URL-адресу. Значением по
 * умолчанию является пустая строка значений.
 * @param method HTTP-метод, который будет использоваться для запроса. Значение по умолчанию - HttpMethod.Get.
 * @param body Тело запроса в формате JSON. Принимаемый ти - @Serializable data class.
 * Значение по умолчанию - null.
 * @param overrideToken Токен, который должен быть включен в заголовок запроса. Если значение равно null,
 * функция извлечет токен из контекста.
 *
 * @return [ApiResponse.Success] или [ApiResponse.Error] в завсисимости от статуса запроса.
 * Ответ ApiResponse содержит статус HTTP, заголовки и обработанные данные ответа
 *
 * @throws SerializationException Если во время сериализации данных ответа возникает ошибка.
 * @throws ServerResponseException Если в ответе сервера содержится ошибка.
 * @throws UnknownHostException Если нет подключения к Интернету.
 * @throws IOException Если во время сетевого подключения возникают ошибки.
 * @throws IllegalArgumentException Если передан не data class или не @Serializable
 */
@Throws(
    SerializationException::class,
    ServerResponseException::class,
    UnknownHostException::class,
    IOException::class,
    IllegalArgumentException::class,
)
suspend inline fun <reified SuccessResponse, reified ErrorResponse> HttpClient.safeRequest(
    path: String,
    context: Context,
    protocol: URLProtocol = URLProtocol.byName[BuildConfig.PROTOCOL]!!,
    host: String = BuildConfig.HOST,
    port: Int = BuildConfig.PORT,
    params: StringValues = StringValues.Empty,
    method: HttpMethod = HttpMethod.Get,
    body: @Serializable Any? = null,
    overrideToken: String? = null,
) = try {
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
            setHeader(overrideToken, context)
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
} catch (e: SerializationException) {
    ApiResponse.Error(
        status = HttpStatusCode(-1, e.message ?: "SerializationException"),
    )
} catch (e: ServerResponseException) {
    ApiResponse.Error(
        status = HttpStatusCode(500, e.message),
    )
} catch (e: UnknownHostException) {
    // Нет интернета
    ApiResponse.Error(
        status = HttpStatusCode(-1000, e.message ?: "No internet connection"),
    )
} catch (e: IOException) {
    // Ошибки соединений
    ApiResponse.Error(
        status = HttpStatusCode(-900, e.message ?: "IO Exception"),
    )
}

suspend fun HttpRequestBuilder.setHeader(
    overrideToken: String?,
    context: Context,
) {
    if (overrideToken != null) {
        header("Authorization", overrideToken)
    } else {
        header("Authorization", Store(Keys.TOKEN, context).get())
    }
}

@Throws(
    IllegalArgumentException::class
)
fun HttpRequestBuilder.body(body: Any?) {
    if(body == null) return

    require(body::class.isData && body::class.annotations.any { it is Serializable }) {
        "Body must be a data class and annotated with @Serializable"
    }

    setBody(body)
}
