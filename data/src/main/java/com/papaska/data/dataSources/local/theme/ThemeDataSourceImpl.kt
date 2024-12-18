package com.papaska.data.dataSources.local.theme

import com.papaska.data.Infrastructure.local.datastore.ProtoDataStore
import com.papaska.domain.entity.local.ThemeEntity

class ThemeDataSourceImpl(
    private val protoDataStore: ProtoDataStore<ThemeEntity>
) : ThemeDataSource {
    override suspend fun read(): ThemeEntity = protoDataStore.get()
    override suspend fun createOrUpdate(themeEntity: ThemeEntity) = protoDataStore.put(themeEntity)
}