package com.cryptoemergency.cryptoemergency.providers.theme

import android.app.Activity
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.repository.store.data.CurrentTheme
import com.cryptoemergency.cryptoemergency.viewModels.ThemeViewModel

var currentTheme by mutableStateOf(CurrentTheme.NULL)

@Composable
fun MainThemeProvider(
    viewModel: ThemeViewModel = hiltViewModel(),
    content: @Composable () -> Unit,
) {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val colors = remember { mutableStateOf(darkPalette) }
    val icons = remember { mutableStateOf(darkIcons) }
    val view = LocalView.current

    // Поставить установленную тему или в соответствии системе
    LaunchedEffect(Unit) {
        // Достать из хранилища
        val themeInStorage = viewModel.getThemeFromStorage()

        currentTheme = if (themeInStorage == CurrentTheme.NULL) {
            if (isSystemInDarkTheme) CurrentTheme.DARK else CurrentTheme.LIGHT
        } else {
            themeInStorage
        }
    }

    // Поменять цветовую гамму у StatusBar, NavigationBar
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            val isLightTheme = when (currentTheme) {
                CurrentTheme.DARK -> false
                CurrentTheme.LIGHT -> true
                CurrentTheme.NULL -> false
            }

            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = isLightTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isLightTheme
        }
    }

    // Рекомпозиция при изменение переменной
    LaunchedEffect(currentTheme) {
        colors.value =
            when (currentTheme) {
                CurrentTheme.DARK -> darkPalette
                CurrentTheme.LIGHT -> lightPalette
                CurrentTheme.NULL -> darkPalette
            }
        icons.value =
            when (currentTheme) {
                CurrentTheme.DARK -> darkIcons
                CurrentTheme.LIGHT -> lightIcons
                CurrentTheme.NULL -> darkIcons
            }
    }

    CompositionLocalProvider(
        LocalColors provides colors.value,
        LocalTypography provides typography,
        LocalShape provides shapes,
        LocalValues provides values,
        LocalIcons provides icons.value,
        content = content,
    )
}
