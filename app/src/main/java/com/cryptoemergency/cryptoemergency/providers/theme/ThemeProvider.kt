package com.cryptoemergency.cryptoemergency.providers.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalColors
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalDimens
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalIcons
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalShape
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalTheme
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalTypography
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.ColorsEntity
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.IconsEntity
import com.cryptoemergency.cryptoemergency.providers.theme.provides.statics.dimens
import com.cryptoemergency.cryptoemergency.providers.theme.provides.icons.darkIcons
import com.cryptoemergency.cryptoemergency.providers.theme.provides.icons.lightIcons
import com.cryptoemergency.cryptoemergency.providers.theme.provides.palettes.darkPalette
import com.cryptoemergency.cryptoemergency.providers.theme.provides.palettes.lightPalette
import com.cryptoemergency.cryptoemergency.providers.theme.provides.statics.shapes
import com.cryptoemergency.cryptoemergency.providers.theme.provides.statics.typography
import com.cryptoemergency.cryptoemergency.providers.theme.viewModels.ThemeViewModel
import com.papaska.core.entity.local.ThemeEntity

/**
 * Provides theme-related data [Theme] to descendant composables.
 *
 * SideEffect: Recompose status bar, navigation bar, [Theme.colors] and [Theme.icons]
 *
 * @param vm The ViewModel responsible for managing the application theme.
 * @param useDarkTheme A boolean flag indicating whether to use the system's dark mode setting.
 * @param content The composable content that will receive the provided theme data.
 */
@Composable
fun ThemeProvider(
    vm: ThemeViewModel,
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val theme by produceState<MutableState<ThemeEntity>?>(null) {
        value = mutableStateOf(vm.initTheme(isSystemInDarkTheme = useDarkTheme))
    }

    val icons = remember { mutableStateOf(
        if(useDarkTheme) darkIcons
        else lightIcons
    ) }
    val colors = remember { mutableStateOf(
        if(useDarkTheme) darkPalette
        else lightPalette
    ) }

    theme?.let {
        when (it.value) {
            ThemeEntity.DARK -> {
                icons.value = darkIcons
                colors.value = darkPalette
            }
            ThemeEntity.LIGHT -> {
                icons.value = lightIcons
                colors.value = lightPalette
            }
            ThemeEntity.AUTO -> Unit
        }

        CompositionLocalProvider(
            LocalTheme provides it,
            LocalColors provides colors.value,
            LocalIcons provides icons.value,
            LocalTypography provides typography,
            LocalShape provides shapes,
            LocalDimens provides dimens,
        ) {
            RecomposeSystemBarsColors()
            RecomposeColorsAndIcons(colors, icons)

            content()
        }
    }
}

/**
 * Recomposes the colors of the system bars (status bar and navigation bar)
 * based on the current theme.
 */
@Composable
private fun RecomposeSystemBarsColors() {
    val view = LocalView.current
    val theme = LocalTheme.current.value

    SideEffect {
        val window = (view.context as Activity).window
        val isLightTheme = when (theme) {
            ThemeEntity.DARK -> false
            ThemeEntity.LIGHT -> true
            ThemeEntity.AUTO -> false
        }

        WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = isLightTheme
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isLightTheme
    }
}

/**
 * Recomposes the colors and icons based on the current theme.
 */
@Composable
private fun RecomposeColorsAndIcons(
    colors: MutableState<ColorsEntity>,
    icons: MutableState<IconsEntity>,
) {
    val theme = LocalTheme.current

    SideEffect {
        when(theme.value) {
            ThemeEntity.DARK -> {
                icons.value = darkIcons
                colors.value = darkPalette
            }
            ThemeEntity.LIGHT -> {
                icons.value = lightIcons
                colors.value = lightPalette
            }
            ThemeEntity.AUTO -> Unit
        }
    }
}