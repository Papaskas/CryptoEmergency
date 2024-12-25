package com.cryptoemergency.cryptoemergency.providers.theme.viewModels

import com.papaska.domain.entity.local.ThemeEntity

class FakeThemeViewModel : ThemeViewModel() {

    override suspend fun initTheme(isSystemInDarkTheme: Boolean): ThemeEntity {
        return when (isSystemInDarkTheme) {
            true -> ThemeEntity.DARK
            else -> ThemeEntity.LIGHT
        }
    }

    /**
     * Получение темы из хранилища
     * */
    override suspend fun getThemeFromStorage() = ThemeEntity.NULL
}