package com.papaska.domain.entity.socialNetwork

data class SocialNetworkEntity(
    val id: Int,
    val socialNetworkName: SocialNetworkName,
    val urlPrefix: String,
    val url: String,
    val description: String?,
    val type: SocialNetworkType,
)
