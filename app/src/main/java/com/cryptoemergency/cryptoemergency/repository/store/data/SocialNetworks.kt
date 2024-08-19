package com.cryptoemergency.cryptoemergency.repository.store.data

import kotlinx.serialization.Serializable

@Serializable
data class SocialNetworks(
    val networks: List<SocialNetwork> = emptyList()
)

@Serializable
data class SocialNetwork(
    val network: Network,
    val data: List<NetworkData>
)

@Serializable
data class NetworkData(
    val url: String,
    val description: String,
)

@Serializable
enum class Network {
    TELEGRAM, VK, INSTAGRAM, TWITTER, FACEBOOK, DISCORD, TWITCH, TIKTOK, LINKEDIN, GITHUB,
}
