package com.papaska.data.http

import com.papaska.domain.entity.config.ServerConfiguration
import com.papaska.domain.entity.http.DomainHttpHeaders
import com.papaska.domain.entity.http.DomainHttpMethod
import com.papaska.domain.entity.http.DomainHttpParams
import com.papaska.domain.entity.http.DomainHttpStatusCode
import com.papaska.domain.entity.http.DomainUrlProtocol
import com.papaska.domain.http.ApiResponse
import com.papaska.domain.repositories.remote.NetworkRepository
import com.papaska.domain.useCases.remote.test.TestRequestUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import javax.inject.Inject

@HiltAndroidTest
class HttpTest {
    @Inject
    lateinit var networkRepository: NetworkRepository

    @Inject
    lateinit var serverConfiguration: ServerConfiguration

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @BeforeEach
    fun init() {
        hiltRule.inject()
    }

    @Serializable
    data class LoginSuccess(
        val token: String,
    )

    @Serializable
    data class ErrorPlaceholder(
        val error: String,
    )

    @Serializable
    data class Headers(
        val host: String,
        val `x-request-start`: String,
        val connection: String,
        val `x-forwarded-proto`: String,
        val `x-forwarded-port`: String,
        val `x-amzn-trace-id`: String,
        val authorization: String,
        val accept: String,
        val `accept-charset`: String,
        val `user-agent`: String,
        val `content-type`: String,
        val `accept-encoding`: String
    )

    @Test
    fun loginSuccess() = runBlocking {
        @Serializable
        data class Request(
            val email: String,
            val password: String,
        )

        val res = request(
            success = LoginSuccess.serializer(),
            error = ErrorPlaceholder.serializer(),
            host = "reqres.in",
            path = "api/login",
            body = Request("eve.holt@reqres.in", "cityslicka"),
            method = DomainHttpMethod.POST,
            protocol = DomainUrlProtocol.HTTPS,
            port = 443,
        )

        assert(res is ApiResponse.Success)
        if (res is ApiResponse.Success) {
            assertEquals(res.status, DomainHttpStatusCode.OK)
            assertEquals(res.body.token, "QpwL5tke4Pnpja7X4")
        }
    }

    @Test
    fun loginError() = runBlocking {
        @Serializable
        data class Request(
            val email: String
        )

        val res = request(
            success = LoginSuccess.serializer(),
            error = ErrorPlaceholder.serializer(),
            host = "reqres.in",
            path = "api/login",
            body = Request("ERROR@mail.ru"),
            method = DomainHttpMethod.POST,
            protocol = DomainUrlProtocol.HTTPS,
            port = 443,
        )

        assert(res is ApiResponse.Error)
        if (res is ApiResponse.Error) {
            assert(res.status == DomainHttpStatusCode.BadRequest)
            assert(res.body?.error == "Missing password")
        }
    }

    @Test
    fun echoBody() = runBlocking {
        @Serializable
        data class Request(
            val message: String
        )

        @Serializable
        data class Response(
            val args: Map<String, String>,
            val data: Map<String, String>,
            val headers: Headers,
            val json: Map<String, String>,
            val url: String
        )

        val res = request(
            success = Response.serializer(),
            error = ErrorPlaceholder.serializer(),
            method = DomainHttpMethod.POST,
            protocol = DomainUrlProtocol.HTTPS,
            host = "postman-echo.com",
            path = "post",
            port = 443,
            body = Request("MESSSAGES")
        )

        assert(res is ApiResponse.Success)
        if (res is ApiResponse.Success) {
            assert(res.status == DomainHttpStatusCode.OK)
            assert(res.body.data["message"] == "MESSSAGES")
        }
    }

    @Test
    fun echoParams() = runBlocking {
        @Serializable
        data class Response(
            val args: Map<String, String>,
            val headers: Headers,
            val url: String
        )

        val res = request(
            success = Response.serializer(),
            error = ErrorPlaceholder.serializer(),
            method = DomainHttpMethod.GET,
            protocol = DomainUrlProtocol.HTTPS,
            host = "postman-echo.com",
            path = "get",
            port = 443,
            params = mapOf(
                "key1" to "value1",
                "test-key" to "test-value"
            )
        )

        assert(res is ApiResponse.Success)
        if (res is ApiResponse.Success) {
            assert(res.status == DomainHttpStatusCode.OK)
            assert(res.body.args["key1"] == "value1")
            assert(res.body.args["key2"] == "value2")
        }
    }

    @Test
    fun echoHeader() = runBlocking {
        @Serializable
        data class EchoResponse(
            val args: Map<String, String>,
            val headers: Headers,
            val url: String
        )

        val token = "Bearer TOKBFYGIAWSBDJY@QG#B@UDSAYSDAYU@@!#"

        val res = request(
            success = EchoResponse.serializer(),
            error = ErrorPlaceholder.serializer(),
            method = DomainHttpMethod.GET,
            protocol = DomainUrlProtocol.HTTPS,
            port = 443,
            host = "postman-echo.com",
            path = "get",
            headers = mapOf("authorization" to token),
        )

        assert(res is ApiResponse.Success)
        if (res is ApiResponse.Success) {
            assert(res.status == DomainHttpStatusCode.OK)
            assert(res.body.headers.authorization == token)
        }
    }

    private suspend fun <Success, Error>request(
        success: KSerializer<Success>,
        error: KSerializer<Error>,
        method: DomainHttpMethod,
        path: String,
        protocol: DomainUrlProtocol,
        host: String,
        port: Int,
        body: Any? = null,
        params: DomainHttpParams = emptyMap(),
        headers: DomainHttpHeaders = emptyMap(),
        onDownload: (bytesSentTotal: Long, contentLength: Long?) -> Unit = { _, _ -> },
        onUpload: (bytesSentTotal: Long, contentLength: Long?) -> Unit = { _, _ -> },
    ) = TestRequestUseCase(
        networkRepository = networkRepository,
        serverConfiguration = serverConfiguration,
    ).invoke(
        success = success,
        error = error,
        method = method,
        path = path,
        protocol = protocol,
        host = host,
        port = port,
        body = body,
        params = params,
        headers = headers,
        onDownload = onDownload,
        onUpload = onUpload,
    )
}
