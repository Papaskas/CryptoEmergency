package com.papaska.domain.repositories.local.db

import com.papaska.domain.entity.socialNetwork.SocialNetworkEntity
import com.papaska.domain.entity.socialNetwork.SocialNetworkName

interface SocialNetworkRepository {
    suspend fun getAll(): List<SocialNetworkEntity>?

    suspend fun getAllBySocialNetworkName(name: SocialNetworkName): List<SocialNetworkEntity>?

    suspend fun insert(socialNetwork: SocialNetworkEntity)

    suspend fun update(socialNetwork: SocialNetworkEntity)

    suspend fun delete(id: Int)
}