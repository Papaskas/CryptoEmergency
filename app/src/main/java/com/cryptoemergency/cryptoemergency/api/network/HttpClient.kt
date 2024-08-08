package com.cryptoemergency.cryptoemergency.api.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val client by lazy {
    HttpClient(OkHttp) {
        install(Logging) {
            level = LogLevel.ALL // Все логировать
        }
        install(ContentNegotiation) {
            json(json)
        }
        install(HttpTimeout) {
            // Сколько запрос ждет
            connectTimeoutMillis = 10_000L // 10 сек
            requestTimeoutMillis = 10_000L // 10 сек
            socketTimeoutMillis = 10_000L // 10 сек
        }
    }
}

val json by lazy {
    Json {
        isLenient = true
        ignoreUnknownKeys = true
    }
}
