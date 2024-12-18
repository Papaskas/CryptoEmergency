package com.papaska.data.dataSources.local.token

import com.papaska.domain.entity.local.TokenEntity

interface TokenDataSource {
    suspend fun read(): TokenEntity
    suspend fun createOrUpdate(token: TokenEntity)
}