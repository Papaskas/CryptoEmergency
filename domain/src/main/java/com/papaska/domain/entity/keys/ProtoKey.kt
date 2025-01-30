package com.papaska.domain.entity.keys

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

interface ProtoKey<T> {
    /**
     * Имя в хранилище, должно быть уникальным
     * */
    val name: String

    /**
     * Хранимое значение
     * */
    @Serializable val value: T

    /**
     * Сериализатор значения
     * */
    val serializer: KSerializer<T>
}