package com.cryptoemergency.cryptoemergency.providers.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.repository.store.data.CurrentTheme

var currentTheme by mutableStateOf(CurrentTheme.NULL)

@Composable
fun MainTheme(
    viewModel: ThemeViewModel = hiltViewModel(),
    content: @Composable () -> Unit,
) {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val colors = remember { mutableStateOf(darkPalette) }

    LaunchedEffect(Unit) {
        // Достать из хранилища
        // Срабатывает только если значение вручную установили
        if (currentTheme == CurrentTheme.NULL) {
            viewModel.getTheme()
        }

        // Установить тему согласно теме из системы если в хранилище пусто
        if (currentTheme == CurrentTheme.NULL) {
            currentTheme = if (isSystemInDarkTheme) CurrentTheme.DARK else CurrentTheme.LIGHT
        }
    }

    LaunchedEffect(currentTheme) {
        colors.value =
            when (currentTheme) {
                CurrentTheme.DARK -> darkPalette
                CurrentTheme.LIGHT -> lightPalette
                CurrentTheme.NULL -> darkPalette
            }
    }

    val shapes =
        Shape(
            padding = 24.dp,
        )

    CompositionLocalProvider(
        LocalColors provides colors.value,
        LocalTypography provides typography,
        LocalShape provides shapes,
        content = content,
    )
}
