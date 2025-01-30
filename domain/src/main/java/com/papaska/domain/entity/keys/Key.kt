package com.papaska.domain.entity.keys

interface Key<T> {
    /**
     * Имя в хранилище, должно быть уникальным
     * */
    val name: String

    /**
     * Значение по умолчанию, которое будет использоваться, если ключ не найден в хранилище
     * */
    val defaultValue: T

    /**
     * Тип ключа
     * */
    val keyType: KeyType
}