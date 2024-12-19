package com.papaska.data.dataSources.local.localStorage

import android.content.Context
import com.papaska.data.infrastructure.local.datastore.ProtoDataStore
import com.papaska.data.infrastructure.local.datastore.keys.ProtoKeyImpl

class ProtoLocalStorageDataSourceImpl<T>(
    private val key: ProtoKeyImpl<T>,
    private val context: Context,
) : LocalStorageDataSource<T> {
    private val store = ProtoDataStore(key, context)

    override suspend fun read(): T = store.read()
    override suspend fun createOrUpdate(entity: T) = store.createOrUpdate(entity)
}