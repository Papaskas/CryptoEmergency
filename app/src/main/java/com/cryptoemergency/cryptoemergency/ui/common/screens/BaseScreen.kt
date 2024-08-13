package com.cryptoemergency.cryptoemergency.ui.common.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
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
 * @param contentAlignment Интерфейс для вычисления положения прямоугольника определенного
 * размера внутри доступного пространства. Выравнивание часто используется для определения
 * выравнивания макета внутри родительского макета.
 * */
@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    redirect: Redirect? = null,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = contentAlignment,
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
