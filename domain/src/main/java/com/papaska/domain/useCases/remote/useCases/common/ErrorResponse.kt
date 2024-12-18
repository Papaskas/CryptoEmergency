package com.papaska.domain.useCases.remote.useCases.common

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val message: String,
)
