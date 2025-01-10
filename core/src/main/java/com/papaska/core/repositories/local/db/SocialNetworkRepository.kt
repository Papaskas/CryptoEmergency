package com.papaska.core.repositories.local.db

import com.papaska.core.entity.db.SocialNetworkEntity
import com.papaska.core.entity.db.SocialNetworkItemEntity
import com.papaska.core.entity.db.SocialNetworkName
import com.papaska.core.entity.db.SocialNetworksEntity

interface SocialNetworkRepository {
    suspend fun getAll(): List<SocialNetworkEntity>

    suspend fun getAllByName(socialNetworkName: SocialNetworkName): SocialNetworkEntity

    suspend fun insertSocialNetwork(socialNetwork: SocialNetworkEntity): Long

    suspend fun getSocialNetworkItems(socialNetworkName: SocialNetworkName): List<SocialNetworkItemEntity>

    suspend fun insertItem(socialNetworkItem: SocialNetworkItemEntity): Long

    suspend fun getAllWithItems(): List<SocialNetworksEntity>

    suspend fun deleteItem(id: Int)
}