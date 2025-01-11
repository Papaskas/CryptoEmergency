package com.papaska.core.entity.db

data class SocialNetworkEntity(
    val id: Int,
    val socialNetworkName: SocialNetworkName,
    val urlPrefix: String,
    val url: String,
    val description: String?,
)
