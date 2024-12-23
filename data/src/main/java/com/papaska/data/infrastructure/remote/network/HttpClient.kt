package com.papaska.data.infrastructure.remote.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.resources.Resources
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Клиент HTTP, настроенный для работы с JSON, логированием и таймаутами.
 */
val httpClient by lazy {
    HttpClient(OkHttp) {
        install(Resources)

        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = 3)
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
            connectTimeoutMillis = 10_000L
            requestTimeoutMillis = 10_000L
            socketTimeoutMillis = 10_000L
        }
    }
}

/**
 * Сериализатор/десериализатор JSON с гибкой конфигурацией.
 * Позволяет игнорировать неизвестные ключи и использовать нестрогую проверку JSON.
 */
val json by lazy {
    Json {
        isLenient = true
        ignoreUnknownKeys = true
    }
}
