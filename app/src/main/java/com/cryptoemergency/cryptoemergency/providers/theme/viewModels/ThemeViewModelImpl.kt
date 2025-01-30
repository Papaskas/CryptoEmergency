package com.cryptoemergency.cryptoemergency.providers.theme.viewModels

import androidx.lifecycle.ViewModel
import com.papaska.domain.entity.local.ThemeEntity
import com.papaska.domain.useCases.storage.LocalStorageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThemeViewModelImpl @Inject constructor(
    private val themeStorage: LocalStorageUseCase<ThemeEntity>,
) : ViewModel(), ThemeViewModel {
    /**
     * Инициализация установленной темы. Если есть установленное в хранилище, то взять его, иначе
     * загрузить как на устройстве
     * */
    override suspend fun initTheme(
        isSystemInDarkTheme: Boolean
    ): ThemeEntity {
        val themeInStorage = getThemeFromStorage()

        return if (themeInStorage == ThemeEntity.AUTO) {
            when (isSystemInDarkTheme) {
                true -> ThemeEntity.DARK
                else -> ThemeEntity.LIGHT
            }
        } else themeInStorage
    }

    /**
     * Получение темы из хранилища
     * */
    override suspend fun getThemeFromStorage() = themeStorage.get()
}

