package com.cryptoemergency.cryptoemergency.repository.requests.login

import kotlinx.serialization.Serializable

@Serializable
data class Request(
    val email: String,
    val password: String,
)
