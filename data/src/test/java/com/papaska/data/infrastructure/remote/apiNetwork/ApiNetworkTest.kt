package com.papaska.data.infrastructure.remote.apiNetwork

import com.papaska.domain.entity.http.DomainHttpHeaders
import com.papaska.domain.entity.http.DomainHttpMethod
import com.papaska.domain.entity.http.DomainHttpParams
import com.papaska.domain.entity.http.DomainHttpStatusCode
import com.papaska.domain.entity.http.DomainUrlProtocol
import com.papaska.domain.http.ApiResponse
import com.papaska.domain.repositories.local.storage.TokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

class ApiNetworkTest {
    private val tokenRepository = mock<TokenRepository>()

    private suspend fun<ErrorResponse, SuccessResponse> request(
        errorResponse: KSerializer<ErrorResponse>,
        successResponse: KSerializer<SuccessResponse>,
        protocol: DomainUrlProtocol = DomainUrlProtocol.HTTPS,
        method: DomainHttpMethod,
        host: String = "postman-echo.com",
        path: String,
        port: Int = 443,
        body: Any? = null,
        params: DomainHttpParams = emptyMap(),
        headers: DomainHttpHeaders = emptyMap(),
        onDownload: (bytesSentTotal: Long, contentLength: Long?) -> Unit = { _: Long, _: Long? -> },
        onUpload: (bytesSentTotal: Long, contentLength: Long?) -> Unit = { _: Long, _: Long? -> },
    ) = KtorHttpClient(
        client = httpClient,
        tokenRepository = tokenRepository
    ).invoke(
        errorResponse = errorResponse,
        successResponse = successResponse,
        host = host,
        path = path,
        port = port,
        method = method,
        protocol = protocol,
        body = body,
        params = params,
        headers = headers,
        onDownload = onDownload,
        onUpload = onUpload,
    )

    @Test
    fun `Ktor framework it's working`() = runTest {
        val job = Job()

        withContext(Dispatchers.IO + job) {
            val res = request(
                errorResponse = HttpResponseEntity.ErrorPlaceholder.serializer(),
                successResponse = HttpResponseEntity.SuccessResponseGet.serializer(),
                method = DomainHttpMethod.GET,
                path = "get",
            )

            assertTrue(res is ApiResponse.Success)

            if (res is ApiResponse.Success) {
                assertEquals(res.status, DomainHttpStatusCode.OK)
                assertEquals(res.body.url, "https://postman-echo.com/get")
            }
        }

        job.cancel()
    }

    @Test
    fun `Checking the health of the body`() = runTest {
        @Serializable
        data class Request(
            val name: String,
            val surname: String,
        )

        val job = Job()

        withContext(Dispatchers.IO + job) {
            val res = request(
                errorResponse = HttpResponseEntity.ErrorPlaceholder.serializer(),
                successResponse = HttpResponseEntity.SuccessResponsePost.serializer(),
                method = DomainHttpMethod.POST,
                path = "post",
                body = Request(
                    name = "NAME",
                    surname = "SURNAME"
                )
            )

            assertTrue(res is ApiResponse.Success)

            if (res is ApiResponse.Success) {
                assertEquals(res.status, DomainHttpStatusCode.OK)

                assertEquals(res.body.data["name"], "NAME")
                assertEquals(res.body.data["surname"], "SURNAME")
            }
        }

        job.cancel()
    }

//    @Test
//    fun `Checking the health of the arguments`() = runTest {
//        val res = request(
//            errorResponse = HttpResponseEntity.ErrorPlaceholder.serializer(),
//            successResponse = HttpResponseEntity.SuccessResponsePost.serializer(),
//            method = DomainHttpMethod.POST,
//            path = "post",
//            params = mapOf(
//                "foo" to "bar",
//                "foo2" to "bar2",
//                "foo2" to "bar3",
//            ),
//        )
//
//        assertTrue(res is ApiResponse.Success)
//
//        if (res is ApiResponse.Success) {
//            assertEquals(res.status, DomainHttpStatusCode.OK)
//
//            assertEquals(res.body.args["foo"], "bar")
//            assertEquals(res.body.args["foo2"],"bar3")
//        }
//    }
//
//    @Test
//    fun `Checking the health of the headers`() = runTest {
//        val res = request(
//            errorResponse = HttpResponseEntity.ErrorPlaceholder.serializer(),
//            successResponse = HttpResponseEntity.SuccessResponsePost.serializer(),
//            method = DomainHttpMethod.POST,
//            path = "post",
//        )
//
//        assertTrue(res is ApiResponse.Success)
//
//        if (res is ApiResponse.Success) {
//            assertEquals(res.status, DomainHttpStatusCode.OK)
//
//            assertEquals(res.headers["content-type"], listOf("application/json; charset=utf-8"))
//            assertEquals(res.headers["server"], listOf("nginx"))
//        }
//    }


//    @Test
//    fun successResponseData() {
////        @Serializable
////        data class Request(
////            val email: String,
////            val password: String,
////        )
////
////        @Serializable
////        data class SuccessResponse(
////            val token: String,
////        )
//
//        assertEquals(4, 2 + 2)
//
////        val res = request(
////            success = LoginSuccess.serializer(),
////            error = ErrorPlaceholder.serializer(),
////            host = "reqres.in",
////            path = "api/login",
////            body = Request("eve.holt@reqres.in", "cityslicka"),
////            method = DomainHttpMethod.POST,
////            protocol = DomainUrlProtocol.HTTPS,
////            port = 443,
////        )
////
////        assert(res is ApiResponse.Success)
////        if (res is ApiResponse.Success) {
////            assertEquals(res.status, DomainHttpStatusCode.OK)
////            assertEquals(res.body.token, "QpwL5tke4Pnpja7X4")
////        }
//    }

//    @Test
//    fun errorResponseData() {
//        @Serializable
//        data class Request(
//            val email: String
//        )

//        val res = request(
//            success = LoginSuccess.serializer(),
//            error = ErrorPlaceholder.serializer(),
//            host = "reqres.in",
//            path = "api/login",
//            body = Request("ERROR@mail.ru"),
//            method = DomainHttpMethod.POST,
//            protocol = DomainUrlProtocol.HTTPS,
//            port = 443,
//        )
//
//        assert(res is ApiResponse.Error)
//        if (res is ApiResponse.Error) {
//            assert(res.status == DomainHttpStatusCode.BadRequest)
//            assert(res.body?.error == "Missing password")
//        }
    //}

//    @Test
//    fun echoBody() {
//        @Serializable
//        data class Request(
//            val message: String
//        )
//
//        @Serializable
//        data class Response(
//            val args: Map<String, String>,
//            val data: Map<String, String>,
//            val headers: Headers,
//            val json: Map<String, String>,
//            val url: String
//        )
//
////        val res = request(
////            success = Response.serializer(),
////            error = ErrorPlaceholder.serializer(),
////            method = DomainHttpMethod.POST,
////            protocol = DomainUrlProtocol.HTTPS,
////            host = "postman-echo.com",
////            path = "post",
////            port = 443,
////            body = Request("MESSSAGES")
////        )
////
////        assert(res is ApiResponse.Success)
////        if (res is ApiResponse.Success) {
////            assert(res.status == DomainHttpStatusCode.OK)
////            assert(res.body.data["message"] == "MESSSAGES")
////        }
//    }
//
//    @Test
//    fun echoParams() {
//        @Serializable
//        data class Response(
//            val args: Map<String, String>,
//            val headers: Headers,
//            val url: String
//        )
//
////        val res = request(
////            success = Response.serializer(),
////            error = ErrorPlaceholder.serializer(),
////            method = DomainHttpMethod.GET,
////            protocol = DomainUrlProtocol.HTTPS,
////            host = "postman-echo.com",
////            path = "get",
////            port = 443,
////            params = mapOf(
////                "key1" to "value1",
////                "test-key" to "test-value"
////            )
////        )
////
////        assert(res is ApiResponse.Success)
////        if (res is ApiResponse.Success) {
////            assert(res.status == DomainHttpStatusCode.OK)
////            assert(res.body.args["key1"] == "value1")
////            assert(res.body.args["test-key"] == "test-value")
////        }
//    }
//
//    @Test
//    fun echoHeader() {
//        @Serializable
//        data class EchoResponse(
//            val args: Map<String, String>,
//            val headers: Headers,
//            val url: String
//        )
//
//        val token = "Bearer TOKBFYGIAWSBDJY@QG#B@UDSAYSDAYU@@!#"
//
////        val res = request(
////            success = EchoResponse.serializer(),
////            error = ErrorPlaceholder.serializer(),
////            method = DomainHttpMethod.GET,
////            protocol = DomainUrlProtocol.HTTPS,
////            port = 443,
////            host = "postman-echo.com",
////            path = "get",
////            headers = mapOf(
////                "authorization" to token,
////                "accept" to "acceptValue"
////            ),
////        )
////
////        assert(res is ApiResponse.Success)
////        if (res is ApiResponse.Success) {
////            assert(res.status == DomainHttpStatusCode.OK)
////            assert(res.body.headers.authorization == token)
////            assert(res.body.headers.accept == "acceptValue")
////        }
//    }

//    private suspend fun <Success, Error>request(
//        success: KSerializer<Success>,
//        error: KSerializer<Error>,
//        method: DomainHttpMethod,
//        path: String,
//        protocol: DomainUrlProtocol,
//        host: String,
//        port: Int,
//        body: Any? = null,
//        params: DomainHttpParams = emptyMap(),
//        headers: DomainHttpHeaders = emptyMap(),
//        onDownload: (bytesSentTotal: Long, contentLength: Long?) -> Unit = { _, _ -> },
//        onUpload: (bytesSentTotal: Long, contentLength: Long?) -> Unit = { _, _ -> },
//    ) = TestRequestUseCase(
//        networkRepository = networkRepository,
//        serverConfiguration = serverConfiguration,
//    ).invoke(
//        success = success,
//        error = error,
//        method = method,
//        path = path,
//        protocol = protocol,
//        host = host,
//        port = port,
//        body = body,
//        params = params,
//        headers = headers,
//        onDownload = onDownload,
//        onUpload = onUpload,
//    )
}
