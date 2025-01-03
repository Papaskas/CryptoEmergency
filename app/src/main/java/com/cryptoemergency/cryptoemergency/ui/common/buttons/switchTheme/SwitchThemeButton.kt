package com.cryptoemergency.cryptoemergency.ui.common.buttons.switchTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Button
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalTheme
import com.papaska.core.entity.local.ThemeEntity

/**
 * Компонент для переключения темы приложения.
 *
 * @param newTheme Новая тема
 *
 * @sample Sample
 **/
@Composable
fun SwitchThemeButton(
    newTheme: State<ThemeEntity>,
    vm: SwitchThemeViewModel = hiltViewModel(),
    content: @Composable () -> Unit,
) {
    val theme = LocalTheme.current

    LaunchedEffect(newTheme.value) {
        vm.setTheme(
            theme,
            newTheme.value
        )
    }

    content()
}

@Composable
private fun Sample() {
    val localTheme = LocalTheme.current
    val theme = remember { mutableStateOf(localTheme.value) }

    SwitchThemeButton(
        newTheme = theme
    ) {
        Button({
            theme.value = when(localTheme.value) {
                ThemeEntity.DARK -> ThemeEntity.LIGHT
                ThemeEntity.LIGHT -> ThemeEntity.DARK
                ThemeEntity.AUTO -> ThemeEntity.LIGHT
            }
        }) {
            Text("Sample")
        }
    }
}
