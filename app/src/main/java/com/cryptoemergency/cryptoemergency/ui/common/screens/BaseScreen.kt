package com.cryptoemergency.cryptoemergency.ui.common.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController
import com.cryptoemergency.cryptoemergency.providers.theme.Theme

/**
 * Базовый шаблон страницы с начальными отступами и базовой логикой
 *
 * @param redirect Обьект с переменной route, изменение которой производит редирект,
 * и popBackStack блокирующий возращение
 *
 * @param alignment Горизонтальное позиционирование контента
 * */
@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    redirect: Redirect? = null,
    alignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(bottom = Theme.shaped.padding)
            .padding(horizontal = Theme.shaped.padding),
        horizontalAlignment = alignment,
    ) {
        content()
    }

    val navController = LocalNavController.current
    LaunchedEffect(redirect?.route) {
        redirect?.route?.let { url ->
            if (redirect.popBackStack) {
                navController.popBackStack()
            }

            navController.navigate(url)
        }
    }
}
