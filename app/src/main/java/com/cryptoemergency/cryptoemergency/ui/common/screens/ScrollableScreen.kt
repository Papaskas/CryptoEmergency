package com.cryptoemergency.cryptoemergency.ui.common.screens

import androidx.compose.foundation.layout.BoxScope
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
 * @param contentAlignment Интерфейс для вычисления положения прямоугольника определенного
 * размера внутри доступного пространства. Выравнивание часто используется для определения
 * выравнивания макета внутри родительского макета.
 *
 * */
@Composable
fun ScrollableScreen(
    modifier: Modifier = Modifier,
    message: MutableState<String?>,
    contentAlignment: Alignment = Alignment.TopStart,
    redirect: Redirect? = null,
    content: @Composable BoxScope.() -> Unit,
) {
    val scrollState = rememberScrollState()

    Screen(
        modifier = modifier
            .verticalScroll(scrollState)
            .imePadding(), // Чтобы прокручивать то что под клавиатурой
        message = message,
        redirect = redirect,
        contentAlignment = contentAlignment,
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
 * @param contentAlignment Интерфейс для вычисления положения прямоугольника определенного
 * размера внутри доступного пространства. Выравнивание часто используется для определения
 * выравнивания макета внутри родительского макета.
 *
 * */
@Composable
fun ScrollableScreen(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    redirect: Redirect? = null,
    content: @Composable BoxScope.() -> Unit,
) {
    val scrollState = rememberScrollState()

    BaseScreen(
        modifier = modifier
            .verticalScroll(scrollState)
            .imePadding(), // Чтобы прокручивать то что под клавиатурой
        redirect = redirect,
        contentAlignment = contentAlignment,
    ) {
        content()
    }
}
