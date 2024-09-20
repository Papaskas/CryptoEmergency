package com.cryptoemergency.cryptoemergency.repository.requests.createPost

import kotlinx.serialization.Serializable

@Serializable
data class Request (
    val description: String,
    val media: List<Media>
)

@Serializable
data class Media(
    val type: String,
    val originalUrl: String,
)
