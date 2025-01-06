package com.papaska.data.repositories.local

import com.papaska.data.dataSources.localStorage.LocalStorageDataSource
import com.papaska.core.entity.local.UserEntity
import com.papaska.core.repositories.local.storage.UserRepository

class UserRepositoryImpl(private val userDataSource: LocalStorageDataSource<UserEntity>) :
    UserRepository {
    override suspend fun get(): UserEntity = userDataSource.read()
    override suspend fun put(user: UserEntity) = userDataSource.createOrUpdate(user)
}
