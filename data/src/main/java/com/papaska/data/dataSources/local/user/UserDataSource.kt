package com.papaska.data.dataSources.local.user

import com.papaska.domain.entity.local.UserEntity

interface UserDataSource {
    suspend fun read(): UserEntity
    suspend fun createOrUpdate(userEntity: UserEntity)
}