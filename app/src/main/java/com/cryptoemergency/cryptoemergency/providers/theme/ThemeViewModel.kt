package com.cryptoemergency.cryptoemergency.providers.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papaska.domain.entity.local.ThemeEntity
import com.papaska.domain.useCases.local.theme.GetThemeUseCase
import com.papaska.domain.useCases.local.theme.SaveThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val getThemeUseCase: GetThemeUseCase,
    private val saveThemeUseCase: SaveThemeUseCase,
) : ViewModel() {

    /**
     * Получение темы из хранилища
     * */
    suspend fun getThemeFromStorage() = getThemeUseCase()

    /**
     * Переключение темы. С темного на светлый и обратно
     */
    fun toggleTheme() {
        currentTheme = when (currentTheme) {
            ThemeEntity.DARK -> ThemeEntity.LIGHT
            ThemeEntity.LIGHT -> ThemeEntity.DARK
            ThemeEntity.NULL -> ThemeEntity.LIGHT
        }

        viewModelScope.launch(Dispatchers.IO) {
            saveThemeUseCase(currentTheme)
        }
    }

    /**
     * Смена темы на любую
     */
    fun changeTheme(theme: ThemeEntity) {
        currentTheme = theme

        viewModelScope.launch(Dispatchers.IO) {
            saveThemeUseCase(theme)
        }
    }
}

