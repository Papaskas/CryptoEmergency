package com.papaska.domain.useCases.remote.useCases.getPosts

import android.content.Context
import com.cryptoemergency.cryptoemergency.api.data.http.createRequest
import com.cryptoemergency.cryptoemergency.api.data.http.httpClient
import com.cryptoemergency.cryptoemergency.api.domain.model.requests.common.ErrorResponse
import com.cryptoemergency.cryptoemergency.api.domain.model.requests.common.PATH
import io.ktor.http.HttpMethod

suspend fun getPostsRequest(context: Context) =
    httpClient.createRequest<PostsResponse, ErrorResponse>(
        path = "$PATH/posts",
        method = HttpMethod.Get,
        context = context,
    )
