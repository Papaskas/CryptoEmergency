package com.papaska.core.http

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
     * @property status [DomainHttpStatusCode] Код статуса HTTP.
     * @property body [Success] Тело ответа.
     * @property headers [DomainHttpHeaders] Заголовки ответа.
     */
    data class Success<Success>(
        val status: DomainHttpStatusCode,
        val body: Success,
        val headers: DomainHttpHeaders,
    ) : ApiResponse<Success, Nothing>

    /**
     * Ошибочный ответ HTTP-запроса.
     *
     * @property status [DomainHttpStatusCode] Код статуса HTTP.
     * @property body [Error] Необязательное тело ошибки.
     * @property headers [DomainHttpHeaders] Необязательные заголовки ответа.
     */
    data class Error<Error>(
        val status: DomainHttpStatusCode,
        val body: Error? = null,
        val headers: DomainHttpHeaders? = null,
    ) : ApiResponse<Nothing, Error>
}
