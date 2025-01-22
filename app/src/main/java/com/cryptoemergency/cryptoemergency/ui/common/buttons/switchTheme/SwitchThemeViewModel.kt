package com.cryptoemergency.cryptoemergency.ui.common.buttons.switchTheme

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papaska.domain.entity.local.ThemeEntity
import com.papaska.domain.useCases.storage.theme.SaveThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SwitchThemeViewModel @Inject constructor(
    private val saveThemeUseCase: SaveThemeUseCase
) : ViewModel() {
    fun setTheme(
        localThemeEntity: MutableState<ThemeEntity>,
        newTheme: ThemeEntity,
    ) {
        require(newTheme != ThemeEntity.AUTO) { "${ThemeEntity.AUTO} forbidden" }

        localThemeEntity.value = newTheme

        viewModelScope.launch {
            saveThemeUseCase(newTheme)
        }
    }
}