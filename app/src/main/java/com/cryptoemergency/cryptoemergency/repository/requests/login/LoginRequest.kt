package com.cryptoemergency.cryptoemergency.repository.requests.login

import android.content.Context
import com.cryptoemergency.cryptoemergency.api.http.createRequest
import com.cryptoemergency.cryptoemergency.api.http.httpClient
import com.cryptoemergency.cryptoemergency.repository.requests.common.ErrorResponse
import com.cryptoemergency.cryptoemergency.repository.requests.common.PATH
import com.cryptoemergency.cryptoemergency.repository.requests.common.SuccessResponse
import io.ktor.http.HttpMethod

suspend fun loginRequest(context: Context, email: String, password: String) =
    httpClient.createRequest<SuccessResponse, ErrorResponse>(
        path = "$PATH/login",
        method = HttpMethod.Get,
        context = context,
        body = Request(email, password)
    )
