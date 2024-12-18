package com.papaska.domain.repositories.local

import com.papaska.domain.entity.local.UserEntity

interface UserRepository {
    suspend fun get(): UserEntity
    suspend fun put(user: UserEntity)
}