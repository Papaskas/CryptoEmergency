package com.cryptoemergency.cryptoemergency.lib

import android.util.Log
import io.ktor.http.HttpStatusCode

fun handleServerError(statusCode: HttpStatusCode): String {
    Log.e(
        "handleServerError",
        "Error code: ${statusCode.value},\n description: ${statusCode.description}"
    )

    return when (statusCode.value) {
        -1 -> "Ошибка сериализации данных"
        -1000 -> "Нет подключения к интернету"
        -900 -> "Возможная ошибка интернет соединения"
        HttpStatusCode.Forbidden.value -> "Отказано в доступе"
        HttpStatusCode.MethodNotAllowed.value -> "Не разрешенный метод"
        HttpStatusCode.TooManyRequests.value -> "Превышен лимит запросов"
        HttpStatusCode.RequestTimeout.value -> "Превышено время запроса"
        HttpStatusCode.InternalServerError.value -> "Непредвиденная ошибка сервера"
        else -> "Непредвиденная ошибка клиента"
    }
}
