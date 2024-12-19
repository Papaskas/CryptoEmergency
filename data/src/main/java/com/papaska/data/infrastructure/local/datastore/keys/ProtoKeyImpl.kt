package com.papaska.data.infrastructure.local.datastore.keys

import androidx.datastore.core.Serializer
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.papaska.domain.entity.local.ThemeEntity
import com.papaska.domain.entity.local.UserEntity

/**
 * Представление в виде ключей, используемые для хранения и извлечения данных в хранилище настроек.
 * Каждый ключ связан с определенным типом данных, значением по умолчанию и сериализатором.
 *
 * Используется библиотека Proto DataStore
 *
 * @param T Тип значения, связанного с ключом, передается вместе с ключом,
 * типизиурет ответы из хранилища
 *
 * @param key Настройки связанные с ключом.
 *
 * @param defaultValue Значение по умолчанию, которое будет использоваться, если ключ не найден в
 * хранилище настроек
 *
 * @param serializer Сериализатор, используемый для сериализации и десериализации данных.
 */
sealed class ProtoKeyImpl<T>(
    override val key: Preferences.Key<String>,
    override val defaultValue: T,
    override val serializer: Serializer<T>,
): ProtoKey<T> {
    data object USER : ProtoKeyImpl<UserEntity>(
        key = stringPreferencesKey("user"),
        defaultValue = UserEntity(),
        serializer = protoDataStoreSerializer(UserEntity()),
    )

    data object THEME : ProtoKeyImpl<ThemeEntity>(
        key = stringPreferencesKey("theme"),
        defaultValue = ThemeEntity.NULL,
        serializer = protoDataStoreSerializer(ThemeEntity.NULL),
    )
}
