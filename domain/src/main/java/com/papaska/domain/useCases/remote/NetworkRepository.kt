package com.papaska.domain.useCases.remote

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import java.io.IOException
import java.net.UnknownHostException

/**
 * Интерфейс для выполнения HTTP-запросов.
 *
 * Этот интерфейс определяет методы для выполнения запросов к API.
 */
interface NetworkRepository {
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
     * @param method [HttpMethod] HTTP-метод, который будет использоваться для запроса.
     * @param protocol [UrlProtocol] Протокол, который будет использоваться для подключения по URL.
     * @param host [String] Хост сервера API.
     * @param port [Int] Номер порта, который будет использоваться для подключения по URL.
     * @param params [HttpParams] Определяет параметры, которые будут добавлены к URL-адресу.
     * @param body Тело запроса в формате JSON. Принимаемый тип - @Serializable data class.
     * Значение по умолчанию - null.
     * @param overrideToken [String] Токен, который должен быть включен в заголовок запроса. Если значение равно null,
     * функция извлечет токен из контекста.
     * @param onDownload Registers listener to observe download progress.
     * @param onUpload Registers listener to observe upload progress
     *
     * @return [ApiResponse.Success] или [ApiResponse.Error] в завсисимости от статуса запроса.
     * Ответ [ApiResponse] содержит статус HTTP, заголовки и обработанные данные ответа
     *
     * @throws SerializationException В случае ошибки сериализации/десериализации данных.
     * @throws ServerResponseException В случае ошибки сервера.
     * @throws UnknownHostException В случае ошибки разрешения имени хоста.
     * @throws IOException В случае сетевых ошибок.
     */
    suspend fun <SuccessResponse, ErrorResponse> invoke(
        host: String,
        path: String,
        port: Int,
        method: HttpMethod,
        protocol: UrlProtocol,
        params: HttpParams,
        body: @Serializable Any?,
        overrideToken: String?,
        onDownload: (bytesSentTotal: Long, contentLength: Long?) -> Unit = { _, _ -> },
        onUpload: (bytesSentTotal: Long, contentLength: Long?) -> Unit = { _, _, -> },
    ): ApiResponse<out SuccessResponse, out ErrorResponse>
}
