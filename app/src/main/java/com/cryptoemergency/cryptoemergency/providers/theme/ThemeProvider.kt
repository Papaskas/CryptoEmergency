package com.cryptoemergency.cryptoemergency.providers.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.papaska.domain.entity.local.ThemeEntity

var currentTheme by mutableStateOf(ThemeEntity.NULL)

@Composable
fun ThemeProvider(
    vm: ThemeViewModel = hiltViewModel(),
    content: @Composable () -> Unit,
) {
    val colors = remember { mutableStateOf(darkPalette) }
    val icons = remember { mutableStateOf(darkIcons) }

    ThemeStorageOrSystem(vm)
    RecomposeSystemBarsColors()
    RecomposeColorAndIcons(colors, icons)

    CompositionLocalProvider(
        LocalColors provides colors.value,
        LocalTypography provides typography,
        LocalShape provides shapes,
        LocalDimens provides dimens,
        LocalIcons provides icons.value,
    ) { content() }
}

// Поменять цветовую гамму у StatusBar, NavigationBar при рекомпозиции
@Composable
private fun RecomposeSystemBarsColors() {
    val view = LocalView.current

    LaunchedEffect(currentTheme) {
        val window = (view.context as Activity).window
        val isLightTheme = when (currentTheme) {
            ThemeEntity.DARK -> false
            ThemeEntity.LIGHT -> true
            ThemeEntity.NULL -> false
        }

        WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = isLightTheme
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isLightTheme
    }
}

// Поменять иконки и цвета при рекомпозиции
@Composable
private fun RecomposeColorAndIcons(
    colors: MutableState<Colors>,
    icons: MutableState<Icons>,
) {
    // Рекомпозиция при изменение переменной
    LaunchedEffect(currentTheme) {
        colors.value =
            when (currentTheme) {
                ThemeEntity.DARK -> darkPalette
                ThemeEntity.LIGHT -> lightPalette
                ThemeEntity.NULL -> darkPalette
            }
        icons.value =
            when (currentTheme) {
                ThemeEntity.DARK -> darkIcons
                ThemeEntity.LIGHT -> lightIcons
                ThemeEntity.NULL -> darkIcons
            }
    }
}

// Тема из хранилища если есть или системная
@Composable
private fun ThemeStorageOrSystem(
    vm: ThemeViewModel,
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme()
) {
    LaunchedEffect(Unit) {
        // Достать из хранилища

        val themeInStorage = vm.getThemeFromStorage()

        currentTheme = if (themeInStorage == ThemeEntity.NULL) {
            if (isSystemInDarkTheme) ThemeEntity.DARK else ThemeEntity.LIGHT
        } else {
            themeInStorage
        }
    }
}
