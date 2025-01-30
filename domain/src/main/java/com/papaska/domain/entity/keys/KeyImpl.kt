package com.papaska.domain.entity.keys

import com.papaska.domain.entity.local.PinCodeEntity
import com.papaska.domain.entity.local.TokenEntity

/**
 * Представление в виде ключей, используемые для хранения и извлечения данных в локальном хранилище
 *
 * @param T Тип значения, связанного с ключом, передаются вместе с ключом,
 * типизиурет ответы из хранилища
 * @param name Уникальное имя для хранилища.
 * @param defaultValue Значение по умолчанию, которое будет использоваться, если ключ не найден в
 * хранилище настроек
 * @param keyType Один из разрешенных типов хранимого значения
 */
sealed class KeyImpl<T>(
    override val name: String,
    override val defaultValue: T,
    override val keyType: KeyType,
): Key<T> {
    /**
     * Ключ для хранения токена.
     */
    data object TOKEN : KeyImpl<TokenEntity>(
        name = "TOKEN",
        defaultValue = "",
        keyType = KeyType.String
    )

    /**
     * Ключ для хранения PIN-кода.
     */
    data object PIN_CODE : KeyImpl<PinCodeEntity>(
        name = "PIN-CODE",
        defaultValue = "",
        keyType = KeyType.String
    )
}
