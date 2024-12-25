package com.cryptoemergency.cryptoemergency.providers.theme.viewModels

import androidx.lifecycle.ViewModel
import com.papaska.domain.entity.local.ThemeEntity

abstract class ThemeViewModel : ViewModel() {
    /**
     * Инициализация установленной темы. Если есть установленное в хранилише, то взять его, иначе
     * загрузить как на устройстве
     * */
    abstract suspend fun initTheme(isSystemInDarkTheme: Boolean): ThemeEntity

    /**
     * Получение темы из хранилища
     * */
    abstract suspend fun getThemeFromStorage() : ThemeEntity
}