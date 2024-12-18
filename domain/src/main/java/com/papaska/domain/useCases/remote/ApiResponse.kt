package com.papaska.domain.useCases.remote

/**
 * Представляет результат HTTP-запроса.
 *
 * Этот интерфейс определяет два возможных результата: успешный [Success] и ошибочный [Error].
 *
 * @param Success Тип данных, представляющий успешный ответ.
 * @param Error Тип данных, представляющий ошибочный ответ.
 */
sealed interface ApiResponse<Success, Error> {
    /**
     * Успешный ответ HTTP-запроса.
     *
     * @property status [HttpStatusCode] Код статуса HTTP.
     * @property body [Success] Тело ответа.
     * @property headers [HttpHeaders] Заголовки ответа.
     */
    data class Success<Success>(
        val status: HttpStatusCode,
        val body: Success,
        val headers: HttpHeaders,
    ) : ApiResponse<Success, Nothing>

    /**
     * Ошибочный ответ HTTP-запроса.
     *
     * @property status [HttpStatusCode] Код статуса HTTP.
     * @property body [Error] Необязательное тело ошибки.
     * @property headers [HttpHeaders] Необязательные заголовки ответа.
     */
    data class Error<Error>(
        val status: HttpStatusCode,
        val body: Error? = null,
        val headers: HttpHeaders? = null,
    ) : ApiResponse<Nothing, Error>
}
