package com.papaska.data.dataSources.local.localStorage

interface LocalStorageDataSource<T> {
    suspend fun read(): T
    suspend fun createOrUpdate(entity: T)
}