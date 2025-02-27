package com.papaska.data.repositories.local

import android.content.Context
import com.papaska.data.infrastructure.local.datastore.PreferencesDataStore
import com.papaska.domain.entity.keys.Key
import com.papaska.domain.repositories.local.storage.LocalStorageRepository

class PreferencesLocalStorageRepositoryImpl<T>(
    private val key: Key<T>,
    private val context: Context,
): LocalStorageRepository<T> {
    private val store = PreferencesDataStore(key, context)

    /**
     * Асинхронный метод чтения из локального хранилища
     * */
    override suspend fun get(): T = store.read()

    /**
     * Асинхронный метод создания или обновления значения в локальном хранилище
     * */
    override suspend fun put(entity: T) = store.createOrUpdate(entity)

    /**
     * Restores the value to the default value
     * */
    override suspend fun clear() = store.clear()
}
