package com.papaska.data.infrastructure.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.papaska.data.infrastructure.local.datastore.keys.Key
import com.papaska.data.infrastructure.local.datastore.keys.KeyImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

/**
 * Класс для хранения и извлечения данных с использованием хранилища данных
 *
 * @param key [KeyImpl] Ключ для идентификации данных в хранилище данных
 * @param context [Context] Контекст для доступу к хранилишу
 *
 * @constructor Создает новый экземпляр хранилища
 */
class DataStore<T>(
    private val key: Key<T>,
    private val context: Context,
) {

    /**
     * Расширение для создания экземпляра DataStore с использованием Preferences API.
     *
     * @receiver [Context] Контекст приложения.
     * @return [DataStore] Экземпляр DataStore для работы с настройками.
     */
    private val Context.dataStore by preferencesDataStore(name = key.name.name)

    /**
     * Экземляр базы
     */
    private val dataStore = context.dataStore

    /**
     * Извлекает значение, связанное с данным ключом, из хранилища данных.
     *
     * @return [T] значение, связанное с данным ключом. Если ключ не найден, возвращается значение
     * по умолчанию из объекта [KeyImpl].
     */
    suspend fun read(): T {

        return dataStore.data
            .map { preferences ->
                preferences[key.name] ?: key.defaultValue
            }.first()
    }

    /**
     * Сохраняет заданное значение в хранилище данных, используя предоставленный ключ
     *
     * @param entity Значение, которое будет сохранено в хранилище данных
     */
    suspend fun createOrUpdate(entity: T) {

        dataStore.edit { preferences ->
            preferences[key.name] = entity
        }
    }
}
