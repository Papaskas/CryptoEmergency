package com.papaska.data.repositories.local

import com.papaska.data.dataSources.local.user.UserDataSource
import com.papaska.domain.entity.local.UserEntity
import com.papaska.domain.repositories.local.UserRepository

class UserRepositoryImpl(private val userDataSource: UserDataSource) : UserRepository {
    override suspend fun get(): UserEntity = userDataSource.read()
    override suspend fun put(user: UserEntity) = userDataSource.createOrUpdate(user)
}
