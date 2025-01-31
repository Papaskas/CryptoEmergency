package com.papaska.domain.utility

import com.papaska.domain.constants.StringsConstants.URL_PREFIX_DISCORD
import com.papaska.domain.constants.StringsConstants.URL_PREFIX_FACEBOOK
import com.papaska.domain.constants.StringsConstants.URL_PREFIX_GITHUB
import com.papaska.domain.constants.StringsConstants.URL_PREFIX_INSTAGRAM
import com.papaska.domain.constants.StringsConstants.URL_PREFIX_LINKEDIN
import com.papaska.domain.constants.StringsConstants.URL_PREFIX_TELEGRAM
import com.papaska.domain.constants.StringsConstants.URL_PREFIX_TIKTOK
import com.papaska.domain.constants.StringsConstants.URL_PREFIX_TWITCH
import com.papaska.domain.constants.StringsConstants.URL_PREFIX_TWITTER
import com.papaska.domain.constants.StringsConstants.URL_PREFIX_VK
import com.papaska.domain.entity.socialNetwork.SocialNetworkName
import kotlin.jvm.Throws

@Throws(IllegalStateException::class)
fun findSocialNetworkUrlPrefix(socialNetworkName: SocialNetworkName): String {
    return when(socialNetworkName) {
        SocialNetworkName.TELEGRAM -> URL_PREFIX_TELEGRAM
        SocialNetworkName.VK -> URL_PREFIX_VK
        SocialNetworkName.INSTAGRAM -> URL_PREFIX_INSTAGRAM
        SocialNetworkName.TWITTER -> URL_PREFIX_TWITTER
        SocialNetworkName.FACEBOOK -> URL_PREFIX_FACEBOOK
        SocialNetworkName.DISCORD -> URL_PREFIX_DISCORD
        SocialNetworkName.TWITCH -> URL_PREFIX_TWITCH
        SocialNetworkName.TIKTOK -> URL_PREFIX_TIKTOK
        SocialNetworkName.LINKEDIN -> URL_PREFIX_LINKEDIN
        SocialNetworkName.GITHUB -> URL_PREFIX_GITHUB
        SocialNetworkName.NONE -> error("SocialNetworkName cannot be NONE")
    }
}