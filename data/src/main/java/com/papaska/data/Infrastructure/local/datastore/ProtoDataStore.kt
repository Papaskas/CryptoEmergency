package com.papaska.data.Infrastructure.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.papaska.data.old.store.ProtoKeys
import kotlinx.coroutines.flow.first

/**
 * Класс для хранения и извлечения данных с использованием хранилища данных
 *
 * @param key [ProtoKeys] Ключ для идентификации данных в хранилище данных
 * @param context [Context] Контекст для доступу к хранилишу
 *
 * @constructor Создает новый экземпляр хранилища
 */
class ProtoDataStore<T>(
    private val key: ProtoKeys<T>,
    private val context: Context,
) {
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
    suspend fun get(): T = dataStore.data.first()

    /**
     * Сохраняет заданные данные, связанные с заданным ключом.
     *
     * @param value Значение данных, которые будут сохранены.
     */
    suspend fun put(value: T) {
        dataStore.updateData { value }
    }
}
