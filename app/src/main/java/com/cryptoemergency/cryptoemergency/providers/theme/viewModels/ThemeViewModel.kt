package com.cryptoemergency.cryptoemergency.providers.theme.viewModels

import com.papaska.core.entity.local.ThemeEntity

interface ThemeViewModel {
    /**
     * Инициализация установленной темы. Если есть установленное в хранилише, то взять его, иначе
     * загрузить как на устройстве
     * */
    suspend fun initTheme(isSystemInDarkTheme: Boolean): ThemeEntity

    /**
     * Получение темы из хранилища
     * */
    suspend fun getThemeFromStorage() : ThemeEntity
}