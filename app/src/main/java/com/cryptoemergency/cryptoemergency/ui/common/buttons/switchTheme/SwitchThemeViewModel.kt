package com.cryptoemergency.cryptoemergency.ui.common.buttons.switchTheme

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.ColorsEntity
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.IconsEntity
import com.cryptoemergency.cryptoemergency.providers.theme.provides.icons.darkIcons
import com.cryptoemergency.cryptoemergency.providers.theme.provides.icons.lightIcons
import com.cryptoemergency.cryptoemergency.providers.theme.provides.palettes.darkPalette
import com.cryptoemergency.cryptoemergency.providers.theme.provides.palettes.lightPalette
import com.papaska.core.entity.local.ThemeEntity
import com.papaska.core.useCases.local.theme.SaveThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SwitchThemeViewModel @Inject constructor(
    private val saveThemeUseCase: SaveThemeUseCase
) : ViewModel() {
    val theme = mutableStateOf<ThemeEntity?>(null)
    val colors = mutableStateOf<ColorsEntity?>(null)
    val icons = mutableStateOf<IconsEntity?>(null)

    fun setTheme(
        newTheme: ThemeEntity
    ) {
        require(newTheme != ThemeEntity.NULL) { "${ThemeEntity.NULL} forbidden" }

        theme.value = newTheme

        if (newTheme == ThemeEntity.LIGHT) {
            icons.value = lightIcons
            colors.value = lightPalette
        }
        else if(newTheme == ThemeEntity.DARK) {
            icons.value = darkIcons
            colors.value = darkPalette
        }

        viewModelScope.launch {
            saveThemeUseCase(newTheme)
        }
    }
}