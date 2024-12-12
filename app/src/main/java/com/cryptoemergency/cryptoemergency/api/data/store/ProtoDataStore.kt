package com.cryptoemergency.cryptoemergency.api.data.store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.cryptoemergency.cryptoemergency.api.domain.repository.StorageRepository
import com.cryptoemergency.cryptoemergency.api.domain.model.store.ProtoKeys
import kotlinx.coroutines.flow.first

/**
 * Класс для хранения и извлечения данных с использованием хранилища данных
 *
 * @param key Ключ для идентификации данных в хранилище данных
 * @param context контекст приложения
 *
 * @constructor Создает новый экземпляр хранилища
 */
class ProtoDataStore<T>(
    private val key: ProtoKeys<T>,
    private val context: Context,
) : StorageRepository<T>() {
    private val Context.dataStore: DataStore<T> by dataStore(
        fileName = key.toString(),
        serializer = key.serializer,
    )

    /**
    * Экземляр базы
    */
    private val dataStore: DataStore<T> = context.dataStore

    /**
     * Извлекает сохраненные данные, связанные с данным ключом
     *
     * @return Возвращает сохраненные данные типа T.
     */
    override suspend fun get(): T = dataStore.data.first()

    /**
     * Сохраняет заданные данные, связанные с заданным ключом.
     *
     * @param value Значение данных, которые будут сохранены.
     */
    override suspend fun put(value: T) {
        dataStore.updateData { value }
    }
}
