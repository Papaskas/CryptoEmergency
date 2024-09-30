package com.cryptoemergency.cryptoemergency.repository.requests.getPosts

import android.content.Context
import com.cryptoemergency.cryptoemergency.api.http.createRequest
import com.cryptoemergency.cryptoemergency.api.http.httpClient
import com.cryptoemergency.cryptoemergency.repository.requests.common.ErrorResponse
import com.cryptoemergency.cryptoemergency.repository.requests.common.PATH
import io.ktor.http.HttpMethod

suspend fun getPostsRequest(context: Context) =
    httpClient.createRequest<PostsResponse, ErrorResponse>(
        path = "$PATH/posts",
        method = HttpMethod.Get,
        context = context,
    )
