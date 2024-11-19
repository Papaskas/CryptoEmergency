package com.cryptoemergency.cryptoemergency.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.cryptoemergency.cryptoemergency.providers.localNavController.NavControllerProvider
import com.cryptoemergency.cryptoemergency.providers.localSnackBar.SnackBarProvider
import com.cryptoemergency.cryptoemergency.providers.theme.FakeThemeViewModel
import com.cryptoemergency.cryptoemergency.providers.theme.ThemeProvider
import com.cryptoemergency.cryptoemergency.providers.theme.ThemeViewModel
import com.cryptoemergency.cryptoemergency.repository.store.data.CurrentTheme

/**
 * Стандартный шаблон для [Preview]
 * */
@Composable
fun CommonPreview(
    theme: CurrentTheme = CurrentTheme.DARK,
    content: @Composable () ->  Unit
) {
    val context = LocalContext.current

    val vm: ThemeViewModel = FakeThemeViewModel(context, theme)

    ThemeProvider(vm) {
        NavControllerProvider {
            SnackBarProvider {
                Screen {
                    Box(
                        modifier = Modifier.padding(it).fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        content()
                    }
                }
            }
        }
    }
}
