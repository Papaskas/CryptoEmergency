package com.papaska.domain.useCases.remote.useCases.createPost

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
