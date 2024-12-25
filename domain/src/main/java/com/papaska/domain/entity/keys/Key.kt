package com.papaska.domain.entity.keys

interface Key<T> {
    val name: String
    val defaultValue: T
    val keyType: KeyType
}