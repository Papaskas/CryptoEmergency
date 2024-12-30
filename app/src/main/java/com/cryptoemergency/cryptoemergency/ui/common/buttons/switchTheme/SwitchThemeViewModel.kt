package com.cryptoemergency.cryptoemergency.ui.common.buttons.switchTheme

import android.util.Log
import androidx.compose.runtime.MutableState
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
    val theme = mutableStateOf(ThemeEntity.NULL)
    val colors = mutableStateOf<ColorsEntity?>(null)
    val icons = mutableStateOf<IconsEntity?>(null)

    fun init(
        theme: ThemeEntity,
        colors: ColorsEntity,
        icons: IconsEntity,
    ) {
        this.theme.value = theme
        this.colors.value = colors
        this.icons.value = icons
    }

    fun setTheme(newTheme: ThemeEntity) {
        require(newTheme != ThemeEntity.NULL) { "${ThemeEntity.NULL} forbidden" }

        Log.d("newTheme", newTheme.name)

        when (newTheme) {
            ThemeEntity.LIGHT -> {
                theme.value = newTheme
                icons.value = lightIcons
                colors.value = lightPalette
            }
            ThemeEntity.DARK -> {
                theme.value = newTheme
                icons.value = darkIcons
                colors.value = darkPalette
            }
            ThemeEntity.NULL -> Unit
        }

        viewModelScope.launch {
            saveThemeUseCase(newTheme)
        }
    }
}