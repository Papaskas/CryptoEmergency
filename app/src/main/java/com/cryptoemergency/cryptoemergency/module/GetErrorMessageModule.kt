package com.cryptoemergency.cryptoemergency.module

import android.content.Context
import android.util.Log
import com.cryptoemergency.cryptoemergency.BuildConfig
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.api.http.IOException
import com.cryptoemergency.cryptoemergency.api.http.SerializationException
import com.cryptoemergency.cryptoemergency.api.http.UnknownHostException
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.http.HttpStatusCode
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GetErrorMessageModule {

    @Provides
    @Singleton
    fun provideGetErrorMessage(@ApplicationContext context: Context): (HttpStatusCode) -> String {
        return { statusCode ->
            Log.e(
                "handleServerError",
                "Error code: ${ statusCode.value },\n description: ${ statusCode.description }",
            )

            when (statusCode.value) {
                HttpStatusCode.SerializationException.value -> {
                    if (BuildConfig.DEBUG) {
                        context.getString(R.string.error__serialization_exception)
                    } else {
                        context.getString(R.string.error__internal_server)
                    }
                }
                HttpStatusCode.UnknownHostException.value -> context.getString(R.string.error__unknown_host_exception)
                HttpStatusCode.IOException.value -> context.getString(R.string.error__io_exception)
                HttpStatusCode.Forbidden.value -> context.getString(R.string.error__forbidden)
                HttpStatusCode.MethodNotAllowed.value -> context.getString(R.string.error__method_not_allowed)
                HttpStatusCode.TooManyRequests.value -> context.getString(R.string.error__too_many_request)
                HttpStatusCode.RequestTimeout.value -> context.getString(R.string.error__request_timeout)
                HttpStatusCode.InternalServerError.value -> context.getString(R.string.error__internal_server)
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
}
