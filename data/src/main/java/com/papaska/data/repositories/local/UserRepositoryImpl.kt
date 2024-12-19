package com.papaska.data.repositories.local

import com.papaska.data.dataSources.local.localStorage.LocalStorageDataSource
import com.papaska.domain.entity.local.UserEntity
import com.papaska.domain.repositories.local.UserRepository

class UserRepositoryImpl(private val userDataSource: LocalStorageDataSource<UserEntity>) : UserRepository {
    override suspend fun get(): UserEntity = userDataSource.read()
    override suspend fun put(user: UserEntity) = userDataSource.createOrUpdate(user)
}
