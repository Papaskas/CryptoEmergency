package com.cryptoemergency.cryptoemergency.api.data.store

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.cryptoemergency.cryptoemergency.api.domain.repository.StorageRepository
import com.cryptoemergency.cryptoemergency.api.domain.model.store.Keys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

/**
 * Класс для хранения и извлечения данных с использованием хранилища данных
 *
 * @param key Ключ для идентификации данных в хранилище данных
 * @param context контекст приложения
 *
 * @constructor Создает новый экземпляр хранилища
 */
class DataStore<T>(
    private val key: Keys<T>,
    private val context: Context,
) : StorageRepository<T>() {
    /**
     * Экземляр базы
     */
    private val dataStore = context.dataStore

    /**
     * Извлекает значение, связанное с данным ключом, из хранилища данных.
     *
     * @return [T] значение, связанное с данным ключом. Если ключ не найден, возвращается значение
     * по умолчанию из объекта [Keys].
     */
    override suspend fun get(): T =
        withContext(Dispatchers.IO) {
            dataStore.data
                .map { preferences ->
                    preferences[key.key] ?: key.defaultValue
                }.first()
        }

    /**
     * Сохраняет заданное значение в хранилище данных, используя предоставленный ключ
     *
     * @param value Значение, которое будет сохранено в хранилище данных
     */
    override suspend fun put(value: T) {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[key.key] = value
            }
        }
    }
}

private val Context.dataStore by preferencesDataStore("store")
