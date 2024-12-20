package com.papaska.domain.repositories.local

import com.papaska.domain.entity.local.TokenEntity

interface TokenRepository {
    suspend fun get(): TokenEntity
    suspend fun put(token: TokenEntity)
}
