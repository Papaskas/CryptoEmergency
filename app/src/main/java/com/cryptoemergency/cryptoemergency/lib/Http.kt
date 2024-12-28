package com.cryptoemergency.cryptoemergency.lib

import android.content.Context
import android.util.Log
import com.cryptoemergency.cryptoemergency.BuildConfig
import com.cryptoemergency.cryptoemergency.R
import com.papaska.core.http.DomainHttpStatusCode

object Http {
    fun getDefaultMessages(context: Context, statusCode: DomainHttpStatusCode): String {
        Log.e(
            "Default error message",
            "Error code: ${ statusCode.value },\n description: ${ statusCode.description }",
        )

        return when (statusCode.value) {
            DomainHttpStatusCode.SerializationException.value -> {
                if (BuildConfig.DEBUG) {
                    context.getString(R.string.error__serialization_exception)
                } else {
                    context.getString(R.string.error__internal_server)
                }
            }
            DomainHttpStatusCode.UnknownHostException.value -> context.getString(R.string.error__unknown_host_exception)
            DomainHttpStatusCode.IOException.value -> context.getString(R.string.error__io_exception)
            DomainHttpStatusCode.Forbidden.value -> context.getString(R.string.error__forbidden)
            DomainHttpStatusCode.MethodNotAllowed.value -> context.getString(R.string.error__method_not_allowed)
            DomainHttpStatusCode.TooManyRequests.value -> context.getString(R.string.error__too_many_request)
            DomainHttpStatusCode.RequestTimeout.value -> context.getString(R.string.error__request_timeout)
            DomainHttpStatusCode.InternalServerError.value -> context.getString(R.string.error__internal_server)
            else -> {
                if (BuildConfig.DEBUG) {
                    context.getString(R.string.error__internal_client)
                } else {
                    context.getString(R.string.error__internal_server)
                }
            }
        }
    }

}
