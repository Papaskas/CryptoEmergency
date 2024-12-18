package com.papaska.data.Infrastructure.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.papaska.data.old.store.Keys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

/**
 * Класс для хранения и извлечения данных с использованием хранилища данных
 *
 * @param key [Keys] Ключ для идентификации данных в хранилище данных
 * @param context [Context] Контекст для доступу к хранилишу
 *
 * @constructor Создает новый экземпляр хранилища
 */
class DataStore<T>(
    private val key: Keys<T>,
    private val context: Context,
) {
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
    suspend fun get(): T =
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
    suspend fun put(value: T) {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[key.key] = value
            }
        }
    }
}

/**
 * Расширение для создания экземпляра DataStore с использованием Preferences API.
 *
 * @receiver [Context] Контекст приложения.
 * @return [DataStore] Экземпляр DataStore для работы с настройками.
 */
private val Context.dataStore by preferencesDataStore("store")
