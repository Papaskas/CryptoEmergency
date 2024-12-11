package com.cryptoemergency.cryptoemergency.api.domain.model.requests.createPost

import android.content.Context
import com.cryptoemergency.cryptoemergency.api.data.http.createRequest
import com.cryptoemergency.cryptoemergency.api.data.http.httpClient
import com.cryptoemergency.cryptoemergency.api.domain.model.requests.common.ErrorResponse
import com.cryptoemergency.cryptoemergency.api.domain.model.requests.common.PATH
import io.ktor.http.HttpMethod

suspend fun createPostRequest(context: Context, body: Request) =
    httpClient.createRequest<Response, ErrorResponse>(
        path = "$PATH/posts/create",
        method = HttpMethod.Post,
        context = context,
        body = body,
    )
