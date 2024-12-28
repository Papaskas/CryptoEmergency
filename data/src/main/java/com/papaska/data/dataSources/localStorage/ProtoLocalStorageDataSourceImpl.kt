package com.papaska.data.dataSources.localStorage

import android.content.Context
import com.papaska.data.infrastructure.local.datastore.ProtoDataStore
import com.papaska.core.entity.keys.ProtoKeyImpl

class ProtoLocalStorageDataSourceImpl<T>(
    private val key: ProtoKeyImpl<T>,
    private val context: Context,
) : LocalStorageDataSource<T> {
    private val store = ProtoDataStore(key, context)

    /**
     * Асинхронный метод чтения из локального хранилища
     * */
    override suspend fun read(): T = store.read()

    /**
     * Асинхронный метод создания или обновления значения в локальном хранилище
     * */
    override suspend fun createOrUpdate(entity: T) = store.createOrUpdate(entity)
}