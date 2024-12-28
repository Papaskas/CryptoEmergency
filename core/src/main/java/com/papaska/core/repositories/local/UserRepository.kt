package com.papaska.core.repositories.local

import com.papaska.core.entity.local.UserEntity

interface UserRepository {
    suspend fun get(): UserEntity
    suspend fun put(user: UserEntity)
}