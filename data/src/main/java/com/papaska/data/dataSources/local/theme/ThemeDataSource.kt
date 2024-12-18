package com.papaska.data.dataSources.local.theme

import com.papaska.domain.entity.local.ThemeEntity

interface ThemeDataSource {
    suspend fun read(): ThemeEntity
    suspend fun createOrUpdate(themeEntity: ThemeEntity)
}