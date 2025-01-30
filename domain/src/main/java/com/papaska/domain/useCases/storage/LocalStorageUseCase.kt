package com.papaska.domain.useCases.storage

import com.papaska.domain.repositories.local.storage.LocalStorageRepository

class LocalStorageUseCase<T>(
    private val localStorageRepository: LocalStorageRepository<T>
) {
    suspend fun get() = localStorageRepository.get()

    suspend fun put(value: T) {
        localStorageRepository.put(value)
    }
}