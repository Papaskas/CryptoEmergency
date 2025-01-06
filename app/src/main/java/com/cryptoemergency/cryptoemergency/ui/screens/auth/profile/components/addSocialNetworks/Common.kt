package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.addSocialNetworks

import androidx.annotation.DrawableRes
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import com.cryptoemergency.cryptoemergency.R
import com.papaska.core.entity.db.SocialNetworkName

data class SocialNetworkType(
    val networkName: SocialNetworkName,
    val urlPrefix: MutableState<TextFieldValue>,
    val url: MutableState<TextFieldValue>,
    val description: MutableState<TextFieldValue>,
)

data class SocialNetworkIcon(
    @DrawableRes val icon: Int,
    @DrawableRes val activeIcon: Int,
    val networkName: SocialNetworkName,
)

val socialNetworksIcons = listOf(
    SocialNetworkIcon(
        R.drawable.telegram,
        R.drawable.telegram__active,
        SocialNetworkName.TELEGRAM,
    ),
    SocialNetworkIcon(
        R.drawable.vk,
        R.drawable.vk__active,
        SocialNetworkName.VK,
    ),
    SocialNetworkIcon(
        R.drawable.instagram,
        R.drawable.instagram__active,
        SocialNetworkName.INSTAGRAM,
    ),
    SocialNetworkIcon(
        R.drawable.twitter,
        R.drawable.twitter__active,
        SocialNetworkName.TWITTER,
    ),
    SocialNetworkIcon(
        R.drawable.facebook,
        R.drawable.facebook__active,
        SocialNetworkName.FACEBOOK,
    ),
    SocialNetworkIcon(
        R.drawable.discord,
        R.drawable.discord__active,
        SocialNetworkName.DISCORD,
    ),
    SocialNetworkIcon(
        R.drawable.twitch,
        R.drawable.twitch__active,
        SocialNetworkName.TWITCH,
    ),
    SocialNetworkIcon(
        R.drawable.tiktok,
        R.drawable.tiktok__active,
        SocialNetworkName.TIKTOK,
    ),
    SocialNetworkIcon(
        R.drawable.linkedin,
        R.drawable.linkedin__active,
        SocialNetworkName.LINKEDIN,
    ),
    SocialNetworkIcon(
        R.drawable.github,
        R.drawable.github__active,
        SocialNetworkName.GITHUB,
    ),
)
