package com.papaska.core.entity.keys

interface Key<T> {
    val name: String
    val defaultValue: T
    val keyType: KeyType
}