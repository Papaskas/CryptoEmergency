package com.cryptoemergency.cryptoemergency.ui.common.screens

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cryptoemergency.cryptoemergency.providers.localSnackBar.LocalSnackbar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 *
 * Шаблон страницы с начальными отступами и базовой логикой
 *
 * @param message Переменная при изменении которой выводится snackbarMessage.
 *
 * @param redirect Обьект с переменной route, изменение которой производит редирект,
 * и popBackStack блокирующий возращение
 *
 * @param alignment Горизонтальное позиционирование контента
 *
 * */
@Composable
fun Screen(
    modifier: Modifier = Modifier,
    message: MutableState<String?>,
    alignment: Alignment.Horizontal = Alignment.Start,
    redirect: Redirect? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    BaseScreen(
        modifier = modifier,
        redirect = redirect,
        alignment = alignment,
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

/**
 *
 * Шаблон страницы с начальными отступами и базовой логикой
 *
 * @param message Переменная при изменении которой выводится snackbarMessage.
 *
 * @param redirect Обьект с переменной route, изменение которой производит редирект,
 * и popBackStack блокирующий возращение
 *
 * @param alignment Горизонтальное позиционирование контента
 *
 * */
@Composable
fun Screen(
    modifier: Modifier = Modifier,
    message: MutableStateFlow<String?>,
    alignment: Alignment.Horizontal = Alignment.Start,
    redirect: Redirect? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    BaseScreen(
        modifier = modifier,
        redirect = redirect,
        alignment = alignment,
    ) {
        content()
    }

    val scope = rememberCoroutineScope()
    val snackbar = LocalSnackbar.current

    LaunchedEffect(message) {
        scope.launch {
            message.collect { value ->
                value?.let { msg ->
                    snackbar.showSnackbar(
                        message = msg,
                        withDismissAction = true,
                    )

                    message.value = null
                }
            }
        }
    }
}
