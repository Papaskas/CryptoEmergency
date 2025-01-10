package com.papaska.data.repositories.db

import com.papaska.core.entity.db.SocialNetworkEntity
import com.papaska.core.entity.db.SocialNetworkItemEntity
import com.papaska.core.entity.db.SocialNetworkName
import com.papaska.core.repositories.local.db.SocialNetworkRepository
import com.papaska.data.dao.SocialNetworkDao
import com.papaska.data.mappers.SocialNetworkMapper.toData
import com.papaska.data.mappers.SocialNetworkMapper.toDomain

class SocialNetworkRepositoryImpl(
    private val socialNetworkDao: SocialNetworkDao
): SocialNetworkRepository {

    override suspend fun getAll() = socialNetworkDao.getAll().map { it.toDomain() }

    override suspend fun getAllByName(socialNetworkName: SocialNetworkName) =
        socialNetworkDao.getAllByName(socialNetworkName).toDomain()

    override suspend fun insertSocialNetwork(socialNetwork: SocialNetworkEntity) =
        socialNetworkDao.insertSocialNetwork(socialNetwork = socialNetwork.toData())

    override suspend fun getSocialNetworkItems(socialNetworkName: SocialNetworkName) =
        socialNetworkDao.getSocialNetworkItems(socialNetworkName = socialNetworkName).map { it.toDomain() }

    override suspend fun insertItem(socialNetworkItem: SocialNetworkItemEntity) =
        socialNetworkDao.insertItem(socialNetworkItem = socialNetworkItem.toData())

    override suspend fun getAllWithItems() =
        socialNetworkDao.getAllWithItems().map { it.toDomain() }

    override suspend fun deleteItem(id: Int) =
        socialNetworkDao.deleteItem(id = id)
}
