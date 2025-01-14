package com.papaska.data.infrastructure.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.byteArrayPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.papaska.domain.entity.keys.Key
import com.papaska.domain.entity.keys.KeyImpl
import com.papaska.domain.entity.keys.KeyType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

/**
 * Класс для хранения и извлечения данных с использованием хранилища данных.
 *
 * Работает только с [KeyType] типами данных
 *
 * @param key [Key] Ключ для идентификации данных в хранилище данных
 * @param context [Context] Контекст для доступу к хранилишу
 *
 * @constructor Создает новый экземпляр хранилища
 */
internal class DataStore<T>(
    private val key: Key<T>,
    private val context: Context,
) {
    private val keyImpl: Pair<Preferences.Key<T>, T>

    init {
        keyImpl = Pair(
            getPreferencesKey(key.keyType, key.name),
            key.defaultValue
        )
    }

    /**
     * Расширение для создания экземпляра DataStore с использованием Preferences API.
     *
     * @receiver [Context] Контекст приложения.
     * @return [DataStore] Экземпляр DataStore для работы с настройками.
     */
    private val Context.dataStore by preferencesDataStore(name = keyImpl.first.name)

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
                preferences[keyImpl.first] ?: key.defaultValue
            }.first()
    }

    /**
     * Сохраняет заданное значение в хранилище данных, используя предоставленный ключ
     *
     * @param entity Значение, которое будет сохранено в хранилище данных
     */
    suspend fun createOrUpdate(entity: T) {

        dataStore.edit { preferences ->
            preferences[keyImpl.first] = entity
        }
    }

    private fun<T> getPreferencesKey(
        keyType: KeyType,
        name: String,
    ): Preferences.Key<T> = when(keyType) {
        KeyType.Int -> intPreferencesKey(name) as Preferences.Key<T>
        KeyType.Long -> longPreferencesKey(name) as Preferences.Key<T>
        KeyType.Float -> floatPreferencesKey(name) as Preferences.Key<T>
        KeyType.Double -> doublePreferencesKey(name) as Preferences.Key<T>
        KeyType.String -> stringPreferencesKey(name) as Preferences.Key<T>
        KeyType.StringSet -> stringSetPreferencesKey(name) as Preferences.Key<T>
        KeyType.Boolean -> booleanPreferencesKey(name) as Preferences.Key<T>
        KeyType.ByteArray -> byteArrayPreferencesKey(name) as Preferences.Key<T>
    }
}
