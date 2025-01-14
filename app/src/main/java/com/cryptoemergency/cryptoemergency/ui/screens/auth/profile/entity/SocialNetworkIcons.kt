package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.entity

import androidx.annotation.DrawableRes
import com.cryptoemergency.cryptoemergency.R
import com.papaska.domain.entity.socialNetwork.SocialNetworkName

/**
 * Представляет иконки для различных социальных сетей.
 *
 * Этот класс содержит информацию об иконках для различных социальных сетей,
 * таких как Telegram, VK, Instagram и другие. Каждая социальная сеть представлена
 * в виде объекта, содержащего идентификаторы ресурсов для иконок в активном и неактивном состояниях,
 * а также название социальной сети.
 */
sealed class SocialNetworkIcons(
    @DrawableRes val icon: Int,
    @DrawableRes val activeIcon: Int,
    val networkName: SocialNetworkName,
) {
    /**
     * Пустой объект
     * */
    data object NONE: SocialNetworkIcons(
        icon = R.drawable.telegram,
        activeIcon = R.drawable.telegram,
        networkName = SocialNetworkName.NONE,
    )

    data object TELEGRAM: SocialNetworkIcons(
        icon = R.drawable.telegram,
        activeIcon = R.drawable.telegram__active,
        networkName = SocialNetworkName.TELEGRAM,
    )

    data object VK: SocialNetworkIcons(
        icon = R.drawable.vk,
        activeIcon = R.drawable.vk__active,
        networkName = SocialNetworkName.VK,
    )

    data object INSTAGRAM: SocialNetworkIcons(
        icon = R.drawable.instagram,
        activeIcon = R.drawable.instagram__active,
        networkName = SocialNetworkName.INSTAGRAM,
    )

    data object TWITTER: SocialNetworkIcons(
        icon = R.drawable.twitter,
        activeIcon = R.drawable.twitter__active,
        networkName = SocialNetworkName.TWITTER,
    )

    data object FACEBOOK: SocialNetworkIcons(
        icon = R.drawable.facebook,
        activeIcon = R.drawable.facebook__active,
        networkName = SocialNetworkName.FACEBOOK,
    )

    data object DISCORD: SocialNetworkIcons(
        icon = R.drawable.discord,
        activeIcon = R.drawable.discord__active,
        networkName = SocialNetworkName.DISCORD,
    )

    data object TWITCH: SocialNetworkIcons(
        icon = R.drawable.twitch,
        activeIcon = R.drawable.twitch__active,
        networkName = SocialNetworkName.TWITCH,
    )

    data object TIKTOK: SocialNetworkIcons(
        icon = R.drawable.tiktok,
        activeIcon = R.drawable.tiktok__active,
        networkName = SocialNetworkName.TIKTOK,
    )

    data object LINKEDIN: SocialNetworkIcons(
        icon = R.drawable.linkedin,
        activeIcon = R.drawable.linkedin__active,
        networkName = SocialNetworkName.LINKEDIN,
    )

    data object GITHUB: SocialNetworkIcons(
        icon = R.drawable.github,
        activeIcon = R.drawable.github__active,
        networkName = SocialNetworkName.GITHUB,
    )

    companion object {
        fun findByName(name: SocialNetworkName): SocialNetworkIcons {
            return when (name) {
                SocialNetworkName.TELEGRAM -> TELEGRAM
                SocialNetworkName.VK -> VK
                SocialNetworkName.INSTAGRAM -> INSTAGRAM
                SocialNetworkName.TWITTER -> TWITTER
                SocialNetworkName.FACEBOOK -> FACEBOOK
                SocialNetworkName.DISCORD -> DISCORD
                SocialNetworkName.TWITCH -> TWITCH
                SocialNetworkName.TIKTOK -> TIKTOK
                SocialNetworkName.LINKEDIN -> LINKEDIN
                SocialNetworkName.GITHUB -> GITHUB
                SocialNetworkName.NONE -> NONE
            }
        }
    }
}
