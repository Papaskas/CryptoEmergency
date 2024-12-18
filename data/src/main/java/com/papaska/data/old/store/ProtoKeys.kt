package com.papaska.data.old.store

import androidx.datastore.core.Serializer
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.papaska.domain.entity.local.ThemeEntity
import com.papaska.domain.entity.local.TokenEntity
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
 * @param key Настройки связанные с ключом.
 *
 * @param defaultValue Значение по умолчанию, которое будет использоваться, если ключ не найден в
 * хранилище настроек
 *
 * @param serializer Сериализатор, используемый для сериализации и десериализации данных.
 */
sealed class ProtoKeys<T>(
    val key: Preferences.Key<String>,
    val defaultValue: T,
    val serializer: Serializer<T>,
) {
    data object USER : ProtoKeys<TokenEntity>(
        key = stringPreferencesKey("user"),
        defaultValue = TokenEntity(),
        serializer = GenericSerializer(serializer<TokenEntity>(), TokenEntity()),
    )

    data object THEME : ProtoKeys<ThemeEntity>(
        key = stringPreferencesKey("theme"),
        defaultValue = ThemeEntity.NULL,
        serializer = GenericSerializer(serializer<ThemeEntity>(), ThemeEntity.NULL),
    )
}
