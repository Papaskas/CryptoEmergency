package com.cryptoemergency.cryptoemergency.repository.requests.createPost

import android.content.Context
import com.cryptoemergency.cryptoemergency.api.http.createRequest
import com.cryptoemergency.cryptoemergency.api.http.httpClient
import com.cryptoemergency.cryptoemergency.repository.requests.common.ErrorResponse
import com.cryptoemergency.cryptoemergency.repository.requests.common.PATH
import io.ktor.http.HttpMethod

suspend fun createPostRequest(context: Context, body: Request) =
    httpClient.createRequest<Response, ErrorResponse>(
        path = "$PATH/posts/create",
        method = HttpMethod.Post,
        context = context,
        body = body,
    )
