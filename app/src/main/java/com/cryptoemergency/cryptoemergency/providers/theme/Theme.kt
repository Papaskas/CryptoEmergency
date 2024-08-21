package com.cryptoemergency.cryptoemergency.providers.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.repository.store.data.CurrentTheme
import com.cryptoemergency.cryptoemergency.viewModels.ThemeViewModel
import kotlinx.coroutines.runBlocking

var currentTheme by mutableStateOf(CurrentTheme.NULL)

@Composable
fun MainThemeProvider(
    viewModel: ThemeViewModel = hiltViewModel(),
    content: @Composable () -> Unit,
) {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val colors = remember { mutableStateOf(darkPalette) }
    val icons = remember { mutableStateOf(darkIcons) }

    LaunchedEffect(Unit) {
        // Достать из хранилища
        // Срабатывает только если значение вручную установили
        if (currentTheme == CurrentTheme.NULL) {
            viewModel.fetchTheme()
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
