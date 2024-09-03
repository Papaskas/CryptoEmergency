package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.addSocialNetworks

import androidx.annotation.DrawableRes
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import com.cryptoemergency.cryptoemergency.R

enum class NetworkName {
    TELEGRAM, VK, INSTAGRAM, TWITTER, FACEBOOK, DISCORD, TWITCH, TIKTOK, LINKEDIN, GITHUB,
}

data class SocialNetworkType(
    val networkName: NetworkName,
    val urlPrefix: MutableState<TextFieldValue>,
    val url: MutableState<TextFieldValue>,
    val description: MutableState<TextFieldValue>,
)

data class SocialNetworkIconType(
    @DrawableRes val icon: Int,
    @DrawableRes val activeIcon: Int,
    val networkName: NetworkName,
)

val socialNetworksIcons = listOf(
    SocialNetworkIconType(
        R.drawable.telegram,
        R.drawable.telegram__active,
        NetworkName.TELEGRAM,
    ),
    SocialNetworkIconType(
        R.drawable.vk,
        R.drawable.vk__active,
        NetworkName.VK,
    ),
    SocialNetworkIconType(
        R.drawable.instagram,
        R.drawable.instagram__active,
        NetworkName.INSTAGRAM,
    ),
    SocialNetworkIconType(
        R.drawable.twitter,
        R.drawable.twitter__active,
        NetworkName.TWITTER,
    ),
    SocialNetworkIconType(
        R.drawable.facebook,
        R.drawable.facebook__active,
        NetworkName.FACEBOOK,
    ),
    SocialNetworkIconType(
        R.drawable.discord,
        R.drawable.discord__active,
        NetworkName.DISCORD,
    ),
    SocialNetworkIconType(
        R.drawable.twitch,
        R.drawable.twitch__active,
        NetworkName.TWITCH,
    ),
    SocialNetworkIconType(
        R.drawable.tiktok,
        R.drawable.tiktok__active,
        NetworkName.TIKTOK,
    ),
    SocialNetworkIconType(
        R.drawable.linkedin,
        R.drawable.linkedin__active,
        NetworkName.LINKEDIN,
    ),
    SocialNetworkIconType(
        R.drawable.github,
        R.drawable.github__active,
        NetworkName.GITHUB,
    ),
)
