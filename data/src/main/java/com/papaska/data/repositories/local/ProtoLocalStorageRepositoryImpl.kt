package com.papaska.data.repositories.local

import android.content.Context
import com.papaska.data.infrastructure.local.datastore.ProtoDataStore
import com.papaska.domain.entity.keys.ProtoKey
import com.papaska.domain.repositories.local.storage.LocalStorageRepository

class ProtoLocalStorageRepositoryImpl<T>(
    private val key: ProtoKey<T>,
    private val context: Context,
): LocalStorageRepository<T> {
    private val store = ProtoDataStore(key, context)

    /**
     * Асинхронный метод чтения из локального хранилища
     * */
    override suspend fun get(): T = store.read()

    /**
     * Асинхронный метод создания или обновления значения в локальном хранилище
     * */
    override suspend fun put(entity: T) = store.createOrUpdate(entity)

    override suspend fun clear() = store.clear()
}
