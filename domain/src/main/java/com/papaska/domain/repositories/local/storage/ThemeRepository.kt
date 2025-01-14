package com.papaska.domain.repositories.local.storage

import com.papaska.domain.entity.local.ThemeEntity

interface ThemeRepository {
    suspend fun get(): ThemeEntity
    suspend fun put(theme: ThemeEntity)
}
