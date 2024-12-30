package com.cryptoemergency.cryptoemergency.ui.common.buttons.switchTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Button
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalColors
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalIcons
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalTheme
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.ColorsEntity
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.IconsEntity
import com.papaska.core.entity.local.ThemeEntity
import okhttp3.internal.wait

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
    themeEntity: ThemeEntity,
    vm: SwitchThemeViewModel = hiltViewModel(),
    content: @Composable (setTheme: (themeEntity: ThemeEntity) -> Unit) -> Unit,
) {
    vm.init(
        theme = LocalTheme.current,
        colors = LocalColors.current,
        icons = LocalIcons.current,
    )

    CompositionLocalProvider(
        LocalTheme provides themeEntity,
        LocalColors provides vm.colors.value!!,
        LocalIcons provides vm.icons.value!!,
    ) {
        content { vm.setTheme(themeEntity) }
    }
}

@Composable
private fun Sample() {
    val localTheme = LocalTheme.current

    val theme = when(localTheme) {
        ThemeEntity.DARK -> ThemeEntity.LIGHT
        ThemeEntity.LIGHT -> ThemeEntity.DARK
        ThemeEntity.NULL -> ThemeEntity.LIGHT
    }

    SwitchThemeButton(
        themeEntity = theme
    ) {
        Button({ it(theme) }) {
            Text("Sample")
        }
    }
}