package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.socialNetworks

import androidx.annotation.DrawableRes
import com.cryptoemergency.cryptoemergency.R

data class SocialNetworkIconType(
    @DrawableRes val icon: Int,
    @DrawableRes val activeIcon: Int,
    val contentDescription: String,
)

val socialNetworksIcons = listOf(
    SocialNetworkIconType(
        R.drawable.telegram,
        R.drawable.telegram__active,
        "telegram",
    ),
    SocialNetworkIconType(
        R.drawable.vk,
        R.drawable.vk__active,
        "vk",
    ),
    SocialNetworkIconType(
        R.drawable.instagram,
        R.drawable.instagram__active,
        "instagram",
    ),
    SocialNetworkIconType(
        R.drawable.twitter,
        R.drawable.twitter__active,
        "twitter",
    ),
    SocialNetworkIconType(
        R.drawable.facebook,
        R.drawable.facebook__active,
        "facebook",
    ),
    SocialNetworkIconType(
        R.drawable.discord,
        R.drawable.discord__active,
        "discord",
    ),
    SocialNetworkIconType(
        R.drawable.twitch,
        R.drawable.twitch__active,
        "twitch",
    ),
    SocialNetworkIconType(
        R.drawable.tiktok,
        R.drawable.tiktok__active,
        "tiktok",
    ),
    SocialNetworkIconType(
        R.drawable.linkedin,
        R.drawable.linkedin__active,
        "linkedin",
    ),
    SocialNetworkIconType(
        R.drawable.github,
        R.drawable.github__active,
        "github",
    ),
)