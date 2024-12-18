package com.papaska.data.repositories.local

import com.papaska.data.dataSources.local.token.TokenDataSource
import com.papaska.data.dataSources.local.user.UserDataSource
import com.papaska.domain.entity.local.TokenEntity
import com.papaska.domain.repositories.local.TokenRepository
import com.papaska.domain.repositories.local.UserRepository

class TokenRepositoryImpl(private val tokenDataSource: TokenDataSource) : TokenRepository {
    override suspend fun get(): TokenEntity = tokenDataSource.read()
    override suspend fun put(token: TokenEntity) = tokenDataSource.createOrUpdate(token)
}
