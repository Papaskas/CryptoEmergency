package com.papaska.domain.useCases.remote.useCases.createPost

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val message: String,
)
