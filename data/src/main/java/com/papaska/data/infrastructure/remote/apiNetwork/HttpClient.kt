package com.papaska.data.infrastructure.remote.apiNetwork

import com.papaska.data.BuildConfig
import com.papaska.domain.constants.HttpConstants.CONNECT_TIMEOUT_MILLIS
import com.papaska.domain.constants.HttpConstants.MAX_RETRIES_TO_SERVER
import com.papaska.domain.constants.HttpConstants.REQUEST_TIMEOUT_MILLIS
import com.papaska.domain.constants.HttpConstants.SOCKET_TIMEOUT_MILLIS
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

/**
 * Клиент HTTP, настроенный для работы с JSON, логированием и таймаутами.
 */

internal val httpClient by lazy {
    HttpClient(OkHttp) {
        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = MAX_RETRIES_TO_SERVER)
            exponentialDelay()

            modifyRequest { request ->
                request.headers.append("x-retry-count", retryCount.toString())
            }
        }

        install(ContentNegotiation) {
            json(jsonConfig)
        }

        install(Logging) {
            level = if(BuildConfig.DEBUG)
                LogLevel.ALL
            else
                LogLevel.NONE
        }

        install(HttpTimeout) {
            connectTimeoutMillis = CONNECT_TIMEOUT_MILLIS
            requestTimeoutMillis = REQUEST_TIMEOUT_MILLIS
            socketTimeoutMillis = SOCKET_TIMEOUT_MILLIS
        }
    }
}
