package com.cryptoemergency.cryptoemergency.api.domain.model.requests.common

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val message: String,
)
