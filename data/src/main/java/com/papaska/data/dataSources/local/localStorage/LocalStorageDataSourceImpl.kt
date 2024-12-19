package com.papaska.data.dataSources.local.localStorage

import android.content.Context
import com.papaska.data.infrastructure.local.datastore.DataStore
import com.papaska.data.infrastructure.local.datastore.keys.KeyImpl

class LocalStorageDataSourceImpl<T>(
    private val key: KeyImpl<T>,
    private val context: Context,
) : LocalStorageDataSource<T> {
    private val store = DataStore(key, context)

    override suspend fun read(): T = store.read()
    override suspend fun createOrUpdate(entity: T) = store.createOrUpdate(entity)
}