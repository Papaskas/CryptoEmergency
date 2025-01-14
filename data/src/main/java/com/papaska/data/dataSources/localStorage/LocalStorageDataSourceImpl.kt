package com.papaska.data.dataSources.localStorage

import android.content.Context
import com.papaska.data.infrastructure.local.datastore.DataStore
import com.papaska.domain.entity.keys.KeyImpl

class LocalStorageDataSourceImpl<T>(
    private val key: KeyImpl<T>,
    private val context: Context,
) : LocalStorageDataSource<T> {
    private val store = DataStore(key, context)

    /**
     * Асинхронный метод чтения из локального хранилища
     * */

    override suspend fun read(): T = store.read()

    /**
     * Асинхронный метод создания или обновления значения в локальном хранилище
     * */
    override suspend fun createOrUpdate(entity: T) = store.createOrUpdate(entity)
}