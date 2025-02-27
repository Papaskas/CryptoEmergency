package com.papaska.data.network.infrastructure.local.datastore.keys

import com.papaska.domain.entity.keys.Key
import com.papaska.domain.entity.keys.KeyType

sealed class PreferencesKey<T>(
    override val name: String,
    override val defaultValue: T,
    override val keyType: KeyType,
): Key<T> {

    data object SampleString : PreferencesKey<String>(
        name = "SampleString",
        defaultValue = "Default value",
        keyType = KeyType.String
    )

    data object SampleInt : PreferencesKey<Int>(
        name = "SampleInt",
        defaultValue = 0,
        keyType = KeyType.Int
    )

    data object SampleBoolean : PreferencesKey<Boolean>(
        name = "SampleBoolean",
        defaultValue = false,
        keyType = KeyType.Boolean
    )
}
