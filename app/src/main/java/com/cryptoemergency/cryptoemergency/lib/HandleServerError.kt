package com.cryptoemergency.cryptoemergency.lib

import android.util.Log
import com.cryptoemergency.cryptoemergency.BuildConfig
import io.ktor.http.HttpStatusCode

fun handleServerError(statusCode: HttpStatusCode): String {
    Log.e(
        "handleServerError",
        "Error code: ${statusCode.value},\n description: ${statusCode.description}",
    )

    return when (statusCode.value) {
        -1 -> {
            if (BuildConfig.DEBUG) {
                "Ошибка сериализации данных"
            } else {
                "Непредвиденная ошибка -1"
            }
        }
        -1000 -> "Нет подключения к интернету"
        -900 -> "Удаленный сервер недоступен"
        HttpStatusCode.Forbidden.value -> "Отказано в доступе"
        HttpStatusCode.MethodNotAllowed.value -> "Не разрешенный метод"
        HttpStatusCode.TooManyRequests.value -> "Превышен лимит запросов"
        HttpStatusCode.RequestTimeout.value -> "Превышено время ожидания"
        HttpStatusCode.InternalServerError.value -> "Непредвиденная ошибка сервера"
        else -> {
            if (BuildConfig.DEBUG) {
                "Непредвиденная ошибка клиента"
            } else {
                "Непредвиденная ошибка сервера"
            }
        }
    }
}
