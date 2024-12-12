package com.cryptoemergency.cryptoemergency.api.domain.repository

abstract class StorageRepository<T> {
    /**
     * Извлекает значение типа [T], связанное с данным ключом, из хранилища данных.
     *
     * @return [T] значение, связанное с данным ключом. Если ключ не найден, возвращается значение
     * по умолчанию из объекта key.
     */
    abstract suspend fun get(): T

    /**
     * Сохраняет заданное значение в хранилище данных, используя предоставленный ключ
     *
     * @param value Значение, которое будет сохранено в хранилище данных
     */
    abstract suspend fun put(value: T)
}
