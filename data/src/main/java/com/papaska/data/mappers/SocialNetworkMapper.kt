package com.papaska.data.mappers

import com.papaska.core.entity.db.SocialNetworkEntity
import com.papaska.core.entity.db.SocialNetworkItemEntity
import com.papaska.data.models.db.SocialNetworkModel
import com.papaska.data.models.db.SocialNetworkItemModel

internal object SocialNetworkMapper {
    internal fun SocialNetworkModel.toDomain() = SocialNetworkEntity(
        id = this.id,
        name = this.name,
    )

    internal fun SocialNetworkItemModel.toDomain() = SocialNetworkItemEntity(
        id = this.itemId,
        name = this.name,
        url = this.url,
        description = this.description,
    )

    internal fun SocialNetworkEntity.toData() = SocialNetworkModel(
        id = this.id,
        name = this.name,
    )

    internal fun SocialNetworkItemEntity.toData() = SocialNetworkItemModel(
        itemId = this.id,
        name = this.name,
        url = this.url,
        description = this.description,
    )
}
