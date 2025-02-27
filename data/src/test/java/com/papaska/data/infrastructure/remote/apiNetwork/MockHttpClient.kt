package com.papaska.data.infrastructure.remote.apiNetwork

import com.papaska.domain.constants.HttpConstants.CONNECT_TIMEOUT_MILLIS
import com.papaska.domain.constants.HttpConstants.REQUEST_TIMEOUT_MILLIS
import com.papaska.domain.constants.HttpConstants.SOCKET_TIMEOUT_MILLIS
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.toByteArray
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel

val mockHttpClient = HttpClient(MockEngine) {
    install(ContentNegotiation) {
        json()
    }

    install(HttpTimeout) {
        connectTimeoutMillis = CONNECT_TIMEOUT_MILLIS
        requestTimeoutMillis = REQUEST_TIMEOUT_MILLIS
        socketTimeoutMillis = SOCKET_TIMEOUT_MILLIS
    }

    install(Logging) {
        level = LogLevel.ALL
    }

    engine {
        addHandler { request ->
            when (request.url.encodedPath) {
                "/check_serialization" -> {
                    respond(
                        content = ByteReadChannel("""{"message":"success serialize"}"""),
                        status = HttpStatusCode.OK,
                        headers = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString())),
                    )
                }
                "/echo" -> {
                    respond(
                        content = ByteReadChannel(request.body.toByteArray()),
                        status = HttpStatusCode.OK,
                        headers = request.headers,
                    )
                }
                else -> {
                    error("Unhandled ${ request.url.encodedPath }")
                }
            }
        }
    }
}
