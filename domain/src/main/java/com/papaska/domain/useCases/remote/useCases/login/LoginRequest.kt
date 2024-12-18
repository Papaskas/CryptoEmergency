package com.papaska.domain.useCases.remote.useCases.login

import android.content.Context
import com.cryptoemergency.cryptoemergency.api.data.http.createRequest
import com.cryptoemergency.cryptoemergency.api.data.http.httpClient
import com.cryptoemergency.cryptoemergency.api.domain.model.requests.common.ErrorResponse
import com.cryptoemergency.cryptoemergency.api.domain.model.requests.common.PATH
import com.cryptoemergency.cryptoemergency.api.domain.model.requests.common.SuccessResponse
import io.ktor.http.HttpMethod

suspend fun loginRequest(context: Context, email: String, password: String) =
    httpClient.createRequest<SuccessResponse, ErrorResponse>(
        path = "$PATH/login",
        method = HttpMethod.Get,
        context = context,
        body = Request(email, password)
    )
