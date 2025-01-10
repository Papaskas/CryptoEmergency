package com.papaska.core.entity.db

data class SocialNetworksEntity(
    val socialNetwork: SocialNetworkEntity,
    val items: List<SocialNetworkItemEntity>,
)