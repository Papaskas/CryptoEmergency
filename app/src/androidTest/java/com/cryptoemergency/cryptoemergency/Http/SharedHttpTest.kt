package com.cryptoemergency.cryptoemergency.Http

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.cryptoemergency.cryptoemergency.api.network.ApiResponse
import com.cryptoemergency.cryptoemergency.api.network.client
import com.cryptoemergency.cryptoemergency.api.network.safeRequest
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SharedHttpTest {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun loginSuccess() = runBlocking {
        val res = request<LoginSuccess, ErrorPlaceholder>(
            path = "api/login",
            body = ApiLogin("eve.holt@reqres.in", "cityslicka")
        )

        assert(res is ApiResponse.Success)
        if(res is ApiResponse.Success) {
            assert(res.status == HttpStatusCode.OK)
            assert(res.body.token == "QpwL5tke4Pnpja7X4")
        }
    }

    @Test
    fun loginError() = runBlocking {
        @Serializable
        data class Request(
            val email: String
        )

        val res = request<LoginSuccess, ErrorPlaceholder>(
            path = "api/login",
            body = Request("ERROR@mail.ru")
        )

        assert(res is ApiResponse.Error)
        if(res is ApiResponse.Error) {
            //assert(res.status == HttpStatusCode.BadRequest)
            assert(res.body?.error == "Missing password")
        }
    }

    private suspend inline fun <reified Success, reified Error> request(
        path: String,
        body: Any? = null,
        method: HttpMethod = HttpMethod.Post,
    ) =
        client.safeRequest<Success, Error>(
            host = "reqres.in",
            port = 443,
            path = path,
            body = body,
            protocol = URLProtocol.HTTPS,
            method = method,
            context = context,
        )
}

@Serializable
data class ApiLogin(
    val email: String,
    val password: String,
)

@Serializable
data class LoginSuccess(
    val token: String,
)

object SuccessPlaceholder
data class ErrorPlaceholder(
    val error: String,
)
