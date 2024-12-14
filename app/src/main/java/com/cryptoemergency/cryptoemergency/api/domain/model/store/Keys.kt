package com.cryptoemergency.cryptoemergency.api.domain.model.store

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.cryptoemergency.cryptoemergency.api.data.store.DataStore

/**
 * Представление в виде ключей, используемые для хранения и извлечения данных в библиотеке [DataStore]
 *
 * @param T Тип значения, связанного с ключом, передается вместе с ключом,
 * типизиурет ответы из хранилища
 * @param key Настройки связанные с ключом.
 * @param defaultValue Значение по умолчанию, которое будет использоваться, если ключ не найден в
 * хранилище настроек
 *
 * @see androidx.datastore.preferences.core.Preferences.Key
 */
sealed class Keys<T>(
    val key: Preferences.Key<T>,
    val defaultValue: T,
) {
    /**
     * Ключ для хранения токена.
     */
    data object TOKEN : Keys<String>(
        key = stringPreferencesKey("TOKEN"),
        defaultValue = "",
    )

    /**
     * Ключ для хранения PIN-кода.
     */
    data object PinCode : Keys<String>(
        key = stringPreferencesKey("pin-code"),
        defaultValue = "",
    )
}
