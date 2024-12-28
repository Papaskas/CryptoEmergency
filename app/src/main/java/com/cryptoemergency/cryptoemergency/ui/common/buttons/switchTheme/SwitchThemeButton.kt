package com.cryptoemergency.cryptoemergency.ui.common.buttons.switchTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Button
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalColors
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalIcons
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalTheme
import com.papaska.core.entity.local.ThemeEntity

/**
 * Компонент для переключения темы приложения.

 * Этот компонент предоставляет возможность изменять текущую тему приложения.
 * Он также предоставляет доступ к текущей теме через лямбда-выражение [content].

 * @param content Лямбда-выражение, которое принимает функцию `setTheme` для изменения темы.
 *
 * @sample Sample
 **/
@Composable
fun SwitchThemeButton(
    vm: SwitchThemeViewModel = hiltViewModel(),
    content: @Composable (setTheme: (themeEntity: ThemeEntity) -> Unit) -> Unit,
) {
    val localTheme = LocalTheme.current
    val localColors = LocalColors.current
    val localIcons = LocalIcons.current

    vm.theme.value = localTheme
    vm.colors.value = localColors
    vm.icons.value = localIcons

    CompositionLocalProvider(
        LocalTheme provides vm.theme.value!!,
        LocalColors provides vm.colors.value!!,
        LocalIcons provides vm.icons.value!!,
    ) {
        content { vm.setTheme(localTheme) }
    }
}

@Composable
private fun Sample() {
    val localTheme = LocalTheme.current

    SwitchThemeButton { setTheme ->
        Button({
            val theme = when(localTheme) {
                ThemeEntity.DARK -> ThemeEntity.LIGHT
                ThemeEntity.LIGHT -> ThemeEntity.DARK
                ThemeEntity.NULL -> ThemeEntity.LIGHT
            }

            setTheme(theme)
        }) {
            Text("Sample")
        }
    }
}