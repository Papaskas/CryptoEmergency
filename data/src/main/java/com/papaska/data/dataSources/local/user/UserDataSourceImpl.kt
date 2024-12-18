package com.papaska.data.dataSources.local.user

import com.papaska.data.Infrastructure.local.datastore.ProtoDataStore
import com.papaska.domain.entity.local.TokenEntity

class UserDataSourceImpl(
    private val protoDataStore: ProtoDataStore<TokenEntity>
) : UserDataSource {
    override suspend fun read(): TokenEntity = protoDataStore.get()
    override suspend fun put(userEntity: TokenEntity) = protoDataStore.put(userEntity)
}