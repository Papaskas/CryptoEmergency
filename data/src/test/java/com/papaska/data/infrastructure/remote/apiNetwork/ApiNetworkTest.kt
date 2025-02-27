package com.papaska.data.infrastructure.remote.apiNetwork

import com.papaska.domain.entity.http.DomainHttpHeaders
import com.papaska.domain.entity.http.DomainHttpMethod
import com.papaska.domain.entity.http.DomainHttpParams
import com.papaska.domain.entity.http.DomainHttpStatusCode
import com.papaska.domain.entity.http.DomainUrlProtocol
import com.papaska.domain.entity.local.TokenEntity
import com.papaska.domain.http.ApiResponse
import com.papaska.domain.repositories.local.storage.LocalStorageRepository
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

class ApiNetworkTest {
    private val tokenRepository = mock<LocalStorageRepository<TokenEntity>>()

    @Test
    fun `ApiNetwork health test`() = runTest {
        @Serializable
        data class RequestBody(
            val name: String,
            val surname: String,
        )

        val res = request(
            errorResponse = MockResponse.ErrorPlaceholder.serializer(),
            successResponse = MockResponse.SuccessResponsePost.serializer(),
            method = DomainHttpMethod.POST,
            path = "post",
            body = RequestBody(
                name = "NAME",
                surname = "SURNAME"
            ),
            params = mapOf(
                "foo" to "bar",
                "foo2" to "bar2",
                "foo2" to "bar3",
            ),
        )

        assertTrue(res is ApiResponse.Success)

        if (res is ApiResponse.Success) {
            assertEquals(res.status, DomainHttpStatusCode.OK)
            assertEquals(res.body.url, "https://postman-echo.com/post?foo=bar&foo2=bar3")

            assertEquals(res.body.data["name"], "NAME")
            assertEquals(res.body.data["surname"], "SURNAME")

            assertEquals(res.body.args["foo"], "bar")
            assertEquals(res.body.args["foo2"],"bar3")

            assertEquals(res.headers[DomainHttpHeaders.CONTENT_TYPE], listOf("application/json; charset=utf-8"))
            assertEquals(res.headers[DomainHttpHeaders.SERVER], listOf("nginx"))
        }
    }

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
        headers: Map<DomainHttpHeaders, List<String>> = emptyMap(),
        onDownload: (bytesSentTotal: Long, contentLength: Long?) -> Unit = { _: Long, _: Long? -> },
        onUpload: (bytesSentTotal: Long, contentLength: Long?) -> Unit = { _: Long, _: Long? -> },
    ) = KtorHttpClient(
        client = httpClient,
        tokenRepository = tokenRepository,
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
}
