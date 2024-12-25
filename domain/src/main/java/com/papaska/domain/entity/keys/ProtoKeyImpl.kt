package com.papaska.domain.entity.keys

import com.papaska.domain.entity.local.ThemeEntity
import com.papaska.domain.entity.local.UserEntity
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer

/**
 * Представление в виде ключей, используемые для хранения и извлечения данных в хранилище настроек.
 * Каждый ключ связан с определенным типом данных, значением по умолчанию и сериализатором.
 *
 * Используется библиотека Proto DataStore
 *
 * @param T Тип значения, связанного с ключом, передается вместе с ключом,
 * типизиурет ответы из хранилища
 *
 * @param name Уникальное имя для хринилища
 *
 * @param value Хранимое значение
 *
 * @param serializer Сериализатор, используемый для сериализации и десериализации данных.
 */
sealed class ProtoKeyImpl<T>(
    override val name: String,
    @Serializable override val value: T,
    override val serializer: KSerializer<T>,
): ProtoKey<T> {

    data object USER : ProtoKeyImpl<UserEntity>(
        name = "USER",
        value = UserEntity(),
        serializer = serializer<UserEntity>(),
    )

    data object THEME : ProtoKeyImpl<ThemeEntity>(
        name = "THEME",
        value = ThemeEntity.NULL,
        serializer = serializer<ThemeEntity>(),
    )
}
