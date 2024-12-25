package com.cryptoemergency.cryptoemergency.providers.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalColors
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalDimens
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalIcons
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalShape
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalTheme
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalTypography
import com.cryptoemergency.cryptoemergency.providers.theme.provides.dimens
import com.cryptoemergency.cryptoemergency.providers.theme.provides.icons.darkIcons
import com.cryptoemergency.cryptoemergency.providers.theme.provides.icons.lightIcons
import com.cryptoemergency.cryptoemergency.providers.theme.provides.palettes.darkPalette
import com.cryptoemergency.cryptoemergency.providers.theme.provides.palettes.lightPalette
import com.cryptoemergency.cryptoemergency.providers.theme.provides.shapes
import com.cryptoemergency.cryptoemergency.providers.theme.provides.typography
import com.cryptoemergency.cryptoemergency.providers.theme.viewModels.ThemeViewModel
import com.papaska.domain.entity.local.ThemeEntity

@Composable
fun ThemeProvider(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    vm: ThemeViewModel = hiltViewModel(),
    content: @Composable () -> Unit,
) {
    val theme by produceState<ThemeEntity?>(null) {
        value = vm.initTheme(isSystemInDarkTheme = useDarkTheme)
    }

    theme?.let {
        val (colors, icons) = when (it) {
            ThemeEntity.DARK -> {
                Pair(darkPalette, darkIcons)
            }
            ThemeEntity.LIGHT -> {
                Pair(lightPalette, lightIcons)
            }
            else -> {
                Pair(lightPalette, lightIcons)
            }
        }

        CompositionLocalProvider(
            LocalTheme provides it,
            LocalColors provides colors,
            LocalIcons provides icons,
            LocalTypography provides typography,
            LocalShape provides shapes,
            LocalDimens provides dimens,
        ) {
            RecomposeSystemBarsColors()

            content()
        }
    }
}

// Рекомпозиция цветовой гаммы у StatusBar, NavigationBar
@Composable
private fun RecomposeSystemBarsColors() {
    val view = LocalView.current
    val theme = LocalTheme.current

    SideEffect {
        val window = (view.context as Activity).window
        val isLightTheme = when (theme) {
            ThemeEntity.DARK -> false
            ThemeEntity.LIGHT -> true
            ThemeEntity.NULL -> false
        }

        WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = isLightTheme
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isLightTheme
    }
}
