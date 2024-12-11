package com.cryptoemergency.cryptoemergency.api.domain.model.requests.getToken

import android.content.Context
import com.cryptoemergency.cryptoemergency.api.data.http.createRequest
import com.cryptoemergency.cryptoemergency.api.data.http.httpClient
import com.cryptoemergency.cryptoemergency.api.domain.model.requests.common.ErrorResponse
import com.cryptoemergency.cryptoemergency.api.domain.model.requests.common.PATH
import io.ktor.http.HttpMethod

suspend fun getTokenRequest(context: Context) =
    httpClient.createRequest<SuccessResponse, ErrorResponse>(
        path = "$PATH/guest",
        method = HttpMethod.Post,
        context = context,
    )
