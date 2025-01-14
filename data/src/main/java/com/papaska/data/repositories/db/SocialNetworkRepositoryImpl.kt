package com.papaska.data.repositories.db

import com.papaska.domain.entity.socialNetwork.SocialNetworkEntity
import com.papaska.domain.entity.socialNetwork.SocialNetworkName
import com.papaska.domain.repositories.local.db.SocialNetworkRepository
import com.papaska.data.dao.SocialNetworkDao
import com.papaska.data.mappers.SocialNetworkMapper.toData
import com.papaska.data.mappers.SocialNetworkMapper.toDomain

class SocialNetworkRepositoryImpl(
    private val socialNetworkDao: SocialNetworkDao
): SocialNetworkRepository {

    override suspend fun getAll() = socialNetworkDao.getAll()?.map { it.toDomain() }

    override suspend fun getAllBySocialNetworkName(name: SocialNetworkName) =
        socialNetworkDao.getAllBySocialNetworkName(name)?.map { it.toDomain() }

    override suspend fun insert(socialNetwork: SocialNetworkEntity) =
        socialNetworkDao.insert(socialNetwork = socialNetwork.toData())

    override suspend fun update(socialNetwork: SocialNetworkEntity) =
        socialNetworkDao.update(socialNetwork = socialNetwork.toData())

    override suspend fun delete(id: Int) =
        socialNetworkDao.delete(id = id)
}
