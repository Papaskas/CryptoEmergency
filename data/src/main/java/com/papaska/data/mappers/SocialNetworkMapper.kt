package com.papaska.data.mappers

import com.papaska.domain.entity.socialNetwork.SocialNetworkEntity
import com.papaska.domain.utility.findSocialNetworkUrlPrefix
import com.papaska.data.models.db.SocialNetworkModel

internal object SocialNetworkMapper {
    internal fun SocialNetworkEntity.toData() = SocialNetworkModel(
        id = this.id,
        socialNetworkName = this.socialNetworkName,
        url = this.url,
        description = this.description,
        type = this.type
    )

    internal fun SocialNetworkModel.toDomain() = SocialNetworkEntity(
        id = this.id,
        socialNetworkName = this.socialNetworkName,
        url = this.url,
        description = this.description,
        urlPrefix = findSocialNetworkUrlPrefix(this.socialNetworkName),
        type = this.type
    )
}
