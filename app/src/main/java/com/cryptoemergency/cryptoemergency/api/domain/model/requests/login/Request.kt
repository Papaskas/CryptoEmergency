package com.cryptoemergency.cryptoemergency.api.domain.model.requests.login

import kotlinx.serialization.Serializable

@Serializable
data class Request(
    val email: String,
    val password: String,
)
