package com.papaska.data.repositories.db

import com.papaska.core.entity.db.SocialNetworkItemEntity
import com.papaska.core.entity.db.SocialNetworkName
import com.papaska.core.repositories.local.db.SocialNetworkRepository
import com.papaska.data.dataSources.db.socialNetwork.SocialNetworkDao
import com.papaska.data.mappers.SocialNetworkMapper.toData
import com.papaska.data.mappers.SocialNetworkMapper.toDomain
import kotlinx.coroutines.flow.map

class SocialNetworkRepositoryImpl(
    private val socialNetworkDao: SocialNetworkDao
): SocialNetworkRepository {
    override fun getAll() = socialNetworkDao.getAll().map { it -> it.map { it.toDomain() } }

    override fun getAllBySocialName(socialNetworkName: SocialNetworkName) =
        socialNetworkDao.getAllBySocialName(socialNetworkName).map { it -> it.map { it.toDomain() } }

    override suspend fun insertItem(vararg socialNetworkItem: SocialNetworkItemEntity): Boolean {
        val typesArray = socialNetworkItem.map {
            it.toData()
        }.toTypedArray()

        return socialNetworkDao.insertItem(*typesArray)
    }

    override suspend fun updateItemById(id: Int, url: String, description: String) =
        socialNetworkDao.updateItemById(id, url, description)

    override suspend fun deleteItem(id: Int) = socialNetworkDao.deleteItem(id)
}