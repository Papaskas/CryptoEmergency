package com.papaska.data.repositories.local

import com.papaska.data.dataSources.localStorage.LocalStorageDataSource
import com.papaska.core.entity.local.TokenEntity
import com.papaska.core.repositories.local.storage.TokenRepository

class TokenRepositoryImpl(private val tokenDataSource: LocalStorageDataSource<TokenEntity>) :
    TokenRepository {
    override suspend fun get(): TokenEntity = tokenDataSource.read()
    override suspend fun put(token: TokenEntity) = tokenDataSource.createOrUpdate(token)
}
