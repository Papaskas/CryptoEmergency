package com.papaska.data.infrastructure.local.datastore.keys

import androidx.datastore.core.Serializer
import androidx.datastore.preferences.core.Preferences

interface ProtoKey<T> {
    val key: Preferences.Key<String>
    val defaultValue: T
    val serializer: Serializer<T>
}