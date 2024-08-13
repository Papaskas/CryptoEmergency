package com.cryptoemergency.cryptoemergency.ui.common.screens

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.MutableStateFlow

/**
 *
 * Шаблон прокручиваемой страницы с начальными отступами и базовой логикой
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
fun ScrollableScreen(
    modifier: Modifier = Modifier,
    message: MutableState<String?>,
    alignment: Alignment.Horizontal = Alignment.Start,
    redirect: Redirect? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val scrollState = rememberScrollState()

    Screen(
        modifier = modifier
            .verticalScroll(scrollState)
            .imePadding(), // Чтобы прокручивать то что под клавиатурой
        message = message,
        redirect = redirect,
        alignment = alignment,
    ) {
        content()
    }
}

/**
 *
 * Шаблон прокручиваемой страницы с начальными отступами и базовой логикой
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
fun ScrollableScreen(
    modifier: Modifier = Modifier,
    message: MutableStateFlow<String?>,
    alignment: Alignment.Horizontal = Alignment.Start,
    redirect: Redirect? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val scrollState = rememberScrollState()

    Screen(
        modifier = modifier
            .verticalScroll(scrollState)
            .imePadding(), // Чтобы прокручивать то что под клавиатурой
        message = message,
        redirect = redirect,
        alignment = alignment,
    ) {
        content()
    }
}

/**
 *
 * Шаблон прокручиваемой страницы с начальными отступами и базовой логикой
 *
 * @param redirect Обьект с переменной route, изменение которой производит редирект,
 * и popBackStack блокирующий возращение
 *
 * @param alignment Горизонтальное позиционирование контента
 *
 * */
@Composable
fun ScrollableScreen(
    modifier: Modifier = Modifier,
    alignment: Alignment.Horizontal = Alignment.Start,
    redirect: Redirect? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val scrollState = rememberScrollState()

    BaseScreen(
        modifier = modifier
            .verticalScroll(scrollState)
            .imePadding(), // Чтобы прокручивать то что под клавиатурой
        redirect = redirect,
        alignment = alignment,
    ) {
        content()
    }
}
