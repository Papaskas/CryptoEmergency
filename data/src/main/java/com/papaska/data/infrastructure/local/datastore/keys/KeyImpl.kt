package com.papaska.data.infrastructure.local.datastore.keys

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey

/**
 * Представление в виде ключей, используемые для хранения и извлечения данных в библиотеке [DataStore]
 *
 * @param T Тип значения, связанного с ключом, передается вместе с ключом,
 * типизиурет ответы из хранилища
 * @param name Настройки связанные с ключом.
 * @param defaultValue Значение по умолчанию, которое будет использоваться, если ключ не найден в
 * хранилище настроек
 *
 * @see androidx.datastore.preferences.core.Preferences.Key
 */
sealed class KeyImpl<T>(
    override val name: Preferences.Key<T>,
    override val defaultValue: T,
): Key<T> {
    /**
     * Ключ для хранения токена.
     */
    data object TOKEN : KeyImpl<String>(
        name = stringPreferencesKey("TOKEN"),
        defaultValue = "",
    )

    /**
     * Ключ для хранения PIN-кода.
     */
    data object PIN_CODE : KeyImpl<String>(
        name = stringPreferencesKey("pin-code"),
        defaultValue = "",
    )
}
