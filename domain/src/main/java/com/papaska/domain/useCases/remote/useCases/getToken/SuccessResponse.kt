package com.papaska.domain.useCases.remote.useCases.getToken

import kotlinx.serialization.Serializable

@Serializable
data class SuccessResponse(
    val message: String
)
