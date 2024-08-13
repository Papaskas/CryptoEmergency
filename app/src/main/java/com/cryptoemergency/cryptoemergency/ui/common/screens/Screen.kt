package com.cryptoemergency.cryptoemergency.ui.common.screens

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cryptoemergency.cryptoemergency.providers.localSnackBar.LocalSnackbar
import kotlinx.coroutines.launch

/**
 * Шаблон страницы с начальными отступами и базовой логикой
 *
 * @param message Переменная при изменении которой выводится snackbarMessage.
 *
 * @param redirect Обьект с переменной route, изменение которой производит редирект,
 * и popBackStack блокирующий возращение
 *
 * @param contentAlignment Интерфейс для вычисления положения прямоугольника определенного
 * размера внутри доступного пространства. Выравнивание часто используется для определения
 * выравнивания макета внутри родительского макета.
 * */
@Composable
fun Screen(
    modifier: Modifier = Modifier,
    message: MutableState<String?>,
    contentAlignment: Alignment = Alignment.TopStart,
    redirect: Redirect? = null,
    content: @Composable BoxScope.() -> Unit,
) {
    BaseScreen(
        modifier = modifier,
        redirect = redirect,
        contentAlignment = contentAlignment,
    ) {
        content()
    }

    val scope = rememberCoroutineScope()
    val snackbar = LocalSnackbar.current
    LaunchedEffect(message.value) {
        scope.launch {
            message.value?.let { msg ->
                snackbar.showSnackbar(
                    message = msg,
                    withDismissAction = true,
                )

                message.value = null
            }
        }
    }
}
