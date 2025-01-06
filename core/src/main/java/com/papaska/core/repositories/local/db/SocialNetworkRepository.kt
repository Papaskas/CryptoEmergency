package com.papaska.core.repositories.local.db

import com.papaska.core.entity.db.SocialNetworkEntity
import com.papaska.core.entity.db.SocialNetworkItemEntity
import com.papaska.core.entity.db.SocialNetworkName
import kotlinx.coroutines.flow.Flow

interface SocialNetworkRepository {
    fun getAll(): Flow<List<SocialNetworkEntity>>

    fun getAllBySocialName(socialNetworkName: SocialNetworkName): Flow<List<SocialNetworkEntity>>

    suspend fun insertItem(vararg socialNetworkItem: SocialNetworkItemEntity): Boolean
    suspend fun updateItemById(id: Int, url: String, description: String): Boolean
    suspend fun deleteItem(id: Int): Boolean
}