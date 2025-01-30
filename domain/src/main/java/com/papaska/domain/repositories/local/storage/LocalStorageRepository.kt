package com.papaska.domain.repositories.local.storage

interface LocalStorageRepository<T> {
    suspend fun get(): T
    suspend fun put(entity: T)
}
