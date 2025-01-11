package com.papaska.core.repositories.local.db

import com.papaska.core.entity.db.SocialNetworkEntity
import com.papaska.core.entity.db.SocialNetworkName

interface SocialNetworkRepository {
    suspend fun getAll(): List<SocialNetworkEntity>?

    suspend fun getAllBySocialNetworkName(name: SocialNetworkName): List<SocialNetworkEntity>?

    suspend fun insert(socialNetwork: SocialNetworkEntity)

    suspend fun update(socialNetwork: SocialNetworkEntity)

    suspend fun delete(id: Int)
}