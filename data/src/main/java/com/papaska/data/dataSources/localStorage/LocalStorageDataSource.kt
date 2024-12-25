package com.papaska.data.dataSources.localStorage

/**
 * Интерфейс доступа к локальному хранилищу
 */
interface LocalStorageDataSource<T> {
    /**
     * Асинхронный метод чтения из локального хранилища
     * */
    suspend fun read(): T

    /**
     * Асинхронный метод создания или обновления значения в локальном хранилище
     * */
    suspend fun createOrUpdate(entity: T)
}