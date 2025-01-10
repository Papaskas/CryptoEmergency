package com.papaska.data.models.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.papaska.data.models.db.SocialNetworkItemModel
import com.papaska.data.models.db.SocialNetworkModel

data class SocialNetworksModel(
    @Embedded val socialNetwork: SocialNetworkModel,
    @Relation(
        parentColumn = "socialNetworkName",
        entityColumn = "socialNetworkName",
    )
    val socialNetworks: List<SocialNetworkItemModel>,
)
