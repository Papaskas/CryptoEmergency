package com.papaska.data.infrastructure.local.datastore.keys

import androidx.datastore.preferences.core.Preferences

interface Key<T> {
    val name: Preferences.Key<T>
    val defaultValue: T
}