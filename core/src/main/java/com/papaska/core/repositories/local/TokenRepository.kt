package com.papaska.core.repositories.local

import com.papaska.core.entity.local.TokenEntity

interface TokenRepository {
    suspend fun get(): TokenEntity
    suspend fun put(token: TokenEntity)
}
