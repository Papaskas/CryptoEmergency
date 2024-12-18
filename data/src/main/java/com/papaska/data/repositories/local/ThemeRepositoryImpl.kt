package com.papaska.data.repositories.local

import com.papaska.data.dataSources.local.theme.ThemeDataSource
import com.papaska.domain.entity.local.ThemeEntity
import com.papaska.domain.repositories.local.ThemeRepository

class ThemeRepositoryImpl(private val themeDataSource: ThemeDataSource) : ThemeRepository {
    override suspend fun get(): ThemeEntity = themeDataSource.read()
    override suspend fun put(theme: ThemeEntity) = themeDataSource.createOrUpdate(theme)
}
