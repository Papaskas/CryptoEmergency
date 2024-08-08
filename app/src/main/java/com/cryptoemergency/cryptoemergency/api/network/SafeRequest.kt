package com.cryptoemergency.cryptoemergency.api.network

import android.content.Context
import com.cryptoemergency.cryptoemergency.BuildConfig
import com.cryptoemergency.cryptoemergency.api.store.Store
import com.cryptoemergency.cryptoemergency.repository.store.Keys
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ServerResponseException
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
import kotlinx.serialization.SerializationException
import okio.IOException
import java.net.UnknownHostException

/**
 * Безопасная и универсальная функция HTTP-запроса, которая обрабатывает различные сценарии,
 * такие как исключения при сериализации, исключения при ответе сервера и проблемы с сетевым подключением.
 *
 * @param path Путь к запрашиваемой конечной точке API.
 * @param context Контекст, необходимый для извлечения токена из внутреннего хранилища.
 * @param host хост сервера API. Значение по умолчанию получено из BuildConfig.HOST.
 * @param params определяет параметры, которые будут добавлены к URL-адресу. Значением по
 * умолчанию является пустая строка значений.
 * @param method HTTP-метод, который будет использоваться для запроса. Значение по умолчанию - HttpMethod.Get.
 * @param body Тело запроса в формате JSON. Принимаемый ти - @Serializable data class.
 * Значение по умолчанию - null.
 * @param token токен, который должен быть включен в заголовок запроса. Если значение равно null,
 * функция извлечет токен из контекста.
 *
 * @return ApiResponse.Success если запрос выполнен успешно
 *         ApiResponse.Error если запрос содержит ошибку или статус ответа не является кодом статуса успеха.
 *         Ответ ApiResponse содержит статус HTTP, заголовки и обработанные данные ответа
 *         (ответ об успешном завершении или ответ об ошибке).
 *
 * @throws SerializationException если во время сериализации данных ответа возникает ошибка.
 * @throws ServerResponseException если в ответе сервера содержится ошибка.
 * @throws UnknownHostException если нет подключения к Интернету.
 * @throws IOException если во время сетевого подключения возникают ошибки.
 */
suspend inline fun <reified SuccessResponse, reified ErrorResponse> HttpClient.safeRequest(
    path: String,
    context: Context,
    protocol: URLProtocol = URLProtocol.byName[BuildConfig.PROTOCOL]!!,
    host: String = BuildConfig.HOST,
    port: Int = BuildConfig.PORT,
    params: StringValues = StringValues.Empty,
    method: HttpMethod = HttpMethod.Get,
    body: Any? = null,
    token: String? = null,
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
            if (token != null) {
                header("Authorization", token)
            } else {
                header("Authorization", Store(Keys.TOKEN, context).get())
            }
            if (body != null) setBody(body)
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
