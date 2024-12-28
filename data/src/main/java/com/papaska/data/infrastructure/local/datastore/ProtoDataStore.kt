package com.papaska.data.infrastructure.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.papaska.core.entity.keys.ProtoKey
import com.papaska.core.entity.keys.ProtoKeyImpl
import kotlinx.coroutines.flow.first

/**
 * Класс для хранения и извлечения данных с использованием хранилища данных
 *
 * @param key [ProtoKeyImpl] Ключ для идентификации данных в хранилище данных
 * @param context [Context] Контекст для доступу к хранилишу
 *
 * @constructor Создает новый экземпляр хранилища
 */
internal class ProtoDataStore<T>(
    private val key: ProtoKey<T>,
    private val context: Context,
) {

    private val Context.dataStore: DataStore<T> by dataStore(
        fileName = key.name,
        serializer = ProtoDataStoreSerializer(
            serializer = key.serializer,
            defaultValue = key.value,
        ),
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
    suspend fun read(): T = dataStore.data.first()

    /**
     * Сохраняет заданные данные, связанные с заданным ключом.
     *
     * @param entity Значение данных, которые будут сохранены.
     */
    suspend fun createOrUpdate(entity: T) {
        dataStore.updateData { entity }
    }
}
