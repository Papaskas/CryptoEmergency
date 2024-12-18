package com.papaska.domain.useCases.remote.useCases.login

import kotlinx.serialization.Serializable

@Serializable
data class Request(
    val email: String,
    val password: String,
)
