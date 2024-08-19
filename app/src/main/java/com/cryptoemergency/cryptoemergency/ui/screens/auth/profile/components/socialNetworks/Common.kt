package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.socialNetworks

import androidx.annotation.DrawableRes
import com.cryptoemergency.cryptoemergency.R

data class SocialNetworkIconType(
    @DrawableRes val icon: Int,
    @DrawableRes val activeIcon: Int,
    val contentDescription: String,
)

/**
*
* @param contentDescription обязятельно должен быть заглавными, т.к. по нему потом ищется enum class
*
* */
val socialNetworksIcons = listOf(
    SocialNetworkIconType(
        R.drawable.telegram,
        R.drawable.telegram__active,
        "TELEGRAM",
    ),
    SocialNetworkIconType(
        R.drawable.vk,
        R.drawable.vk__active,
        "VK",
    ),
    SocialNetworkIconType(
        R.drawable.instagram,
        R.drawable.instagram__active,
        "INSTAGRAM",
    ),
    SocialNetworkIconType(
        R.drawable.twitter,
        R.drawable.twitter__active,
        "TWITTER",
    ),
    SocialNetworkIconType(
        R.drawable.facebook,
        R.drawable.facebook__active,
        "FACEBOOK",
    ),
    SocialNetworkIconType(
        R.drawable.discord,
        R.drawable.discord__active,
        "DISCORD",
    ),
    SocialNetworkIconType(
        R.drawable.twitch,
        R.drawable.twitch__active,
        "TWITCH",
    ),
    SocialNetworkIconType(
        R.drawable.tiktok,
        R.drawable.tiktok__active,
        "TIKTOK",
    ),
    SocialNetworkIconType(
        R.drawable.linkedin,
        R.drawable.linkedin__active,
        "LINKEDIN",
    ),
    SocialNetworkIconType(
        R.drawable.github,
        R.drawable.github__active,
        "GITHUB",
    ),
)