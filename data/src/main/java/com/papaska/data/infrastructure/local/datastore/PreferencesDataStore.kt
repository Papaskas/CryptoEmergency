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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

/**
 * Класс для хранения и извлечения данных с использованием хранилища данных.
 *
 * Работает только с [KeyType] типами данных
 *
 * @param key [Key] Ключ для идентификации данных в хранилище данных
 * @param context [Context] Контекст для доступу к хранилищу
 *
 * @constructor Создаёт новый экземпляр хранилища
 */
internal class PreferencesDataStore<T>(
    private val key: Key<T>,
    private val context: Context,
) {
    private val keyImpl: Pair<Preferences.Key<T>, T>

    init {
        keyImpl = Pair(
            getPreferencesKey(key.keyType, key.name) as Preferences.Key<T>,
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
     * Экземпляр базы
     */
    private val dataStore = context.dataStore

    /**
     * Извлекает значение, связанное с данным ключом, из хранилища данных.
     *
     * @return [T] значение, связанное с данным ключом. Если ключ не найден, возвращается значение
     * по умолчанию из объекта [KeyImpl].
     */
    suspend fun read(): T {

        return withContext(Dispatchers.IO) {
            dataStore.data
                .map { preferences ->
                    preferences[keyImpl.first] ?: key.defaultValue
                }.first()
        }
    }

    /**
     * Restores the value to the default value
     * */
    suspend fun clear() {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[keyImpl.first] = key.defaultValue
            }
        }
    }

    /**
     * Сохраняет заданное значение в хранилище данных, используя предоставленный ключ
     *
     * @param entity Значение, которое будет сохранено в хранилище данных
     */
    suspend fun createOrUpdate(entity: T) {

        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[keyImpl.first] = entity
            }
        }
    }

    private fun getPreferencesKey(
        keyType: KeyType,
        name: String,
    ): Preferences.Key<out Any> = when(keyType) {
        KeyType.Int -> intPreferencesKey(name)
        KeyType.Long -> longPreferencesKey(name)
        KeyType.Float -> floatPreferencesKey(name)
        KeyType.Double -> doublePreferencesKey(name)
        KeyType.String -> stringPreferencesKey(name)
        KeyType.StringSet -> stringSetPreferencesKey(name)
        KeyType.Boolean -> booleanPreferencesKey(name)
        KeyType.ByteArray -> byteArrayPreferencesKey(name)
    }
}
