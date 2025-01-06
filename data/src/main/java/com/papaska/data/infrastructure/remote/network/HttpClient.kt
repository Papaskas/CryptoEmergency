package com.papaska.data.infrastructure.remote.network

import com.papaska.core.constants.HttpConstants.CONNECT_TIMEOUT_MILLIS
import com.papaska.core.constants.HttpConstants.JSON_IGNORE_UNKNOWN_KEYS
import com.papaska.core.constants.HttpConstants.JSON_IS_LENIENT
import com.papaska.core.constants.HttpConstants.MAX_RETRIES_TO_SERVER
import com.papaska.core.constants.HttpConstants.REQUEST_TIMEOUT_MILLIS
import com.papaska.core.constants.HttpConstants.SOCKET_TIMEOUT_MILLIS
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

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
            json(json)
        }

        install(Logging) {
//            level = if(BuildConfig.DEBUG) {
            LogLevel.ALL
//            } else {
//                LogLevel.NONE
//            }
        }

        install(HttpTimeout) {
            connectTimeoutMillis = CONNECT_TIMEOUT_MILLIS
            requestTimeoutMillis = REQUEST_TIMEOUT_MILLIS
            socketTimeoutMillis = SOCKET_TIMEOUT_MILLIS
        }
    }
}

/**
 * Сериализатор/десериализатор JSON с гибкой конфигурацией.
 * Позволяет игнорировать неизвестные ключи и использовать нестрогую проверку JSON.
 */
internal val json by lazy {
    Json {
        isLenient = JSON_IS_LENIENT
        ignoreUnknownKeys = JSON_IGNORE_UNKNOWN_KEYS
    }
}
