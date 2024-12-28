package com.cryptoemergency.cryptoemergency.providers.theme.viewModels

import androidx.lifecycle.ViewModel
import com.papaska.core.entity.local.ThemeEntity

class FakeThemeViewModel : ViewModel(), ThemeViewModel {

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