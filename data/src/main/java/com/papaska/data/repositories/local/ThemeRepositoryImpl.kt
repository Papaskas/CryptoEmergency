package com.papaska.data.repositories.local

import com.papaska.data.dataSources.localStorage.LocalStorageDataSource
import com.papaska.core.entity.local.ThemeEntity
import com.papaska.core.repositories.local.storage.ThemeRepository

class ThemeRepositoryImpl(
    private val themeDataSource: LocalStorageDataSource<ThemeEntity>
) : ThemeRepository {
    override suspend fun get(): ThemeEntity = themeDataSource.read()
    override suspend fun put(theme: ThemeEntity) = themeDataSource.createOrUpdate(theme)
}
