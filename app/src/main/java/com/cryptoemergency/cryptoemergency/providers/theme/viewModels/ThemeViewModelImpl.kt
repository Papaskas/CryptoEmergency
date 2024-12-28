package com.cryptoemergency.cryptoemergency.providers.theme.viewModels

import androidx.lifecycle.ViewModel
import com.papaska.core.entity.local.ThemeEntity
import com.papaska.core.useCases.local.theme.GetThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThemeViewModelImpl @Inject constructor(
    private val getThemeUseCase: GetThemeUseCase,
) : ViewModel(), ThemeViewModel {
    /**
     * Инициализация установленной темы. Если есть установленное в хранилише, то взять его, иначе
     * загрузить как на устройстве
     * */
    override suspend fun initTheme(
        isSystemInDarkTheme: Boolean
    ): ThemeEntity {
        val themeInStorage = getThemeFromStorage()

        return if (themeInStorage == ThemeEntity.NULL) {
            when (isSystemInDarkTheme) {
                true -> ThemeEntity.DARK
                else -> ThemeEntity.LIGHT
            }
        } else themeInStorage
    }

    /**
     * Получение темы из хранилища
     * */
    override suspend fun getThemeFromStorage() = getThemeUseCase()
}

