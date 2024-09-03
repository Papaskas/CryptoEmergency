package com.cryptoemergency.cryptoemergency.lib

import android.util.Log
import androidx.compose.runtime.Composable
import com.cryptoemergency.cryptoemergency.BuildConfig
import com.cryptoemergency.cryptoemergency.providers.locale.LocalLocale
import io.ktor.http.HttpStatusCode

@Composable
fun handleServerError(statusCode: HttpStatusCode): String {
    Log.e(
        "handleServerError",
        "Error code: ${statusCode.value},\n description: ${statusCode.description}",
    )
    val locale = LocalLocale.current

    return when (statusCode.value) {
        -1 -> {
            if (BuildConfig.DEBUG) {
                locale.httpExceptionMessage.serializationException
            } else {
                locale.httpExceptionMessage.internalServerError
            }
        }
        -1000 -> locale.httpExceptionMessage.unknownHostException
        -900 -> locale.httpExceptionMessage.IOException
        HttpStatusCode.Forbidden.value -> locale.httpExceptionMessage.forbidden
        HttpStatusCode.MethodNotAllowed.value -> locale.httpExceptionMessage.methodNotAllowed
        HttpStatusCode.TooManyRequests.value -> locale.httpExceptionMessage.tooManyRequests
        HttpStatusCode.RequestTimeout.value -> locale.httpExceptionMessage.requestTimeout
        HttpStatusCode.InternalServerError.value -> locale.httpExceptionMessage.internalServerError
        else -> {
            if (BuildConfig.DEBUG) {
                locale.httpExceptionMessage.internalClientError
            } else {
                locale.httpExceptionMessage.internalServerError
            }
        }
    }
}
