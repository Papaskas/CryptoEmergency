package com.papaska.data.network.infrastructure.local.datastore.keys

import com.papaska.domain.entity.keys.ProtoKey
import com.papaska.domain.entity.local.ThemeEntity
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer

sealed class ProtoKeyImpl<T>(
    override val name: String,
    @Serializable override val value: T,
    override val serializer: KSerializer<T>,
): ProtoKey<T> {

    data object THEME : ProtoKeyImpl<ThemeEntity>(
        name = "THEME",
        value = ThemeEntity.AUTO,
        serializer = serializer<ThemeEntity>(),
    )
}
