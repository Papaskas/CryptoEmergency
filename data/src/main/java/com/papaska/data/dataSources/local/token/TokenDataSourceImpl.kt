package com.papaska.data.dataSources.local.token

import com.papaska.data.Infrastructure.local.datastore.DataStore
import com.papaska.domain.entity.local.TokenEntity

class TokenDataSourceImpl(
    private val protoDataStore: DataStore<TokenEntity>
) : TokenDataSource {
    override suspend fun read(): TokenEntity = protoDataStore.get()
    override suspend fun createOrUpdate(token: TokenEntity) = protoDataStore.put(token)
}