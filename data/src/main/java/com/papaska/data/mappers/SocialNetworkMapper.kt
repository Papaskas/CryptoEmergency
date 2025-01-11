package com.papaska.data.mappers

import com.papaska.core.constants.StringsConstants.URL_PREFIX_DISCORD
import com.papaska.core.constants.StringsConstants.URL_PREFIX_FACEBOOK
import com.papaska.core.constants.StringsConstants.URL_PREFIX_GITHUB
import com.papaska.core.constants.StringsConstants.URL_PREFIX_INSTAGRAM
import com.papaska.core.constants.StringsConstants.URL_PREFIX_LINKEDIN
import com.papaska.core.constants.StringsConstants.URL_PREFIX_TELEGRAM
import com.papaska.core.constants.StringsConstants.URL_PREFIX_TIKTOK
import com.papaska.core.constants.StringsConstants.URL_PREFIX_TWITCH
import com.papaska.core.constants.StringsConstants.URL_PREFIX_TWITTER
import com.papaska.core.constants.StringsConstants.URL_PREFIX_VK
import com.papaska.core.entity.db.SocialNetworkEntity
import com.papaska.core.entity.db.SocialNetworkName
import com.papaska.data.models.db.SocialNetworkModel

internal object SocialNetworkMapper {
    internal fun SocialNetworkEntity.toData() = SocialNetworkModel(
        id = id,
        socialNetworkName = socialNetworkName,
        url = url,
        description = description,
    )

    internal fun SocialNetworkModel.toDomain() = SocialNetworkEntity(
        id = id,
        socialNetworkName = socialNetworkName,
        url = url,
        description = description,
        urlPrefix = findUrlPrefix(socialNetworkName)
    )

    private fun findUrlPrefix(socialNetworkName: SocialNetworkName): String {
        require(socialNetworkName != SocialNetworkName.NONE) { "SocialNetworkName cannot be NONE" }

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
}
