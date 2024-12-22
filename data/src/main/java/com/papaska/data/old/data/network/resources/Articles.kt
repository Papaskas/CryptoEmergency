package com.papaska.data.old.data.network.resources

import com.papaska.data.old.data.network.httpClient
import com.papaska.domain.http.ApiResponse
import com.papaska.domain.http.DomainHttpHeaders
import com.papaska.domain.http.DomainHttpStatusCode
import io.ktor.client.call.body
import io.ktor.client.plugins.onDownload
import io.ktor.client.plugins.onUpload
import io.ktor.client.plugins.resources.get
import io.ktor.http.isSuccess
import io.ktor.resources.Resource
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

@Resource("/articles")
class Articles {

    @Resource("new")
    class New(val parent: Articles = Articles())

    @Resource("{id}")
    class Id(val parent: Articles = Articles(), val id: Long) {
        @Resource("edit")
        class Edit(val parent: Id)
    }
}

suspend fun<Success> test(
    success: KClass<String>,
): ApiResponse<out String, out String> {

    val client = httpClient

    val res = client.get(Articles()) {
        onDownload { bytesSentTotal, contentLength ->

        }

        onUpload { bytesSentTotal, contentLength ->

        }
    }

    val a: Customer = res.body()

    if (res.status.isSuccess()) {
       return ApiResponse.Success(
           status = DomainHttpStatusCode(12, "ASD"),
           body = res.body(),
           headers = DomainHttpHeaders(mapOf())
       )
    } else {
        return ApiResponse.Error(
            status = DomainHttpStatusCode(12, "ASD"),
            body = res.body(),
            headers = DomainHttpHeaders(mapOf())
        )
    }
}

@Serializable
data class Customer(val id: Int, val firstName: String, val lastName: String)