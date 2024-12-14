package com.cryptoemergency.cryptoemergency.api.data.http

import io.ktor.http.HttpStatusCode

/**
 * Кастомный статус HTTP для ошибок сериализации.
 */
val HttpStatusCode.Companion.SerializationException: HttpStatusCode
    get() = HttpStatusCode(SERIALIZATION_EXCEPTION_CODE, "Serialization exception")

/**
 * Кастомный статус HTTP для ошибок ввода-вывода.
 */
val HttpStatusCode.Companion.IOException: HttpStatusCode
    get() = HttpStatusCode(IO_EXCEPTION_CODE, "IO exception")

/**
 * Кастомный статус HTTP для ошибок, связанных с неизвестным хостом.
 */
val HttpStatusCode.Companion.UnknownHostException: HttpStatusCode
    get() = HttpStatusCode(UNKNOWN_HOST_EXCEPTION_CODE, "Unknown host exception")

private const val SERIALIZATION_EXCEPTION_CODE = -1
private const val IO_EXCEPTION_CODE = -900
private const val UNKNOWN_HOST_EXCEPTION_CODE = -1000
