package com.papaska.domain.entity.keys

/**
 * Представление в виде ключей, используемые для хранения и извлечения данных в библиотеке [DataStore]
 *
 * @param T Тип значения, связанного с ключом, передается вместе с ключом,
 * типизиурет ответы из хранилища
 * @param name Настройки связанные с ключом.
 * @param defaultValue Значение по умолчанию, которое будет использоваться, если ключ не найден в
 * хранилище настроек
 *
 */
sealed class KeyImpl<T>(
    override val name: String,
    override val defaultValue: T,
    override val keyType: KeyType,
): Key<T> {
    /**
     * Ключ для хранения токена.
     */
    data object TOKEN : KeyImpl<String>(
        name = "TOKEN",
        defaultValue = "",
        keyType = KeyType.String
    )

    /**
     * Ключ для хранения PIN-кода.
     */
    data object PIN_CODE : KeyImpl<String>(
        name = "PIN-CODE",
        defaultValue = "",
        keyType = KeyType.String
    )
}
