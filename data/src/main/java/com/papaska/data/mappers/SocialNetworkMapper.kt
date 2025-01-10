package com.papaska.data.mappers

import com.papaska.core.entity.db.SocialNetworkEntity
import com.papaska.core.entity.db.SocialNetworkItemEntity
import com.papaska.core.entity.db.SocialNetworksEntity
import com.papaska.data.models.db.SocialNetworkModel
import com.papaska.data.models.db.SocialNetworkItemModel
import com.papaska.data.models.db.relations.SocialNetworksModel

internal object SocialNetworkMapper {
    internal fun SocialNetworkModel.toDomain() = SocialNetworkEntity(
        id = this.id,
        name = this.socialNetworkName,
    )

    internal fun SocialNetworkItemModel.toDomain() = SocialNetworkItemEntity(
        id = this.id,
        name = this.socialNetworkName,
        url = this.url,
        description = this.description,
    )

    internal fun SocialNetworkEntity.toData() = SocialNetworkModel(
        id = this.id,
        socialNetworkName = this.name,
    )

    internal fun SocialNetworkItemEntity.toData() = SocialNetworkItemModel(
        id = this.id,
        socialNetworkName = this.name,
        url = this.url,
        description = this.description,
    )

    internal fun SocialNetworksModel.toDomain() = SocialNetworksEntity(
        socialNetwork = this.socialNetwork.toDomain(),
        items = this.socialNetworks.map { it.toDomain() },
    )

    internal fun SocialNetworksEntity.toData() = SocialNetworksModel(
        socialNetwork = this.socialNetwork.toData(),
        socialNetworks = this.items.map { it.toData() }
    )
}
