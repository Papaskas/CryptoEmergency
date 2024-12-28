package com.papaska.core.repositories.local

import com.papaska.core.entity.local.ThemeEntity

interface ThemeRepository {
    suspend fun get(): ThemeEntity
    suspend fun put(theme: ThemeEntity)
}
