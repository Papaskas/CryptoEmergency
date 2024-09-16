package com.cryptoemergency.cryptoemergency.ui.common

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.providers.localSnackBar.LocalSnackbar
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.bottomBar.BottomBar
import com.cryptoemergency.cryptoemergency.ui.common.topBar.MainTopBar

/**
 * Компонент с настроенной начальной логикой отображения страницы. С верхней и нижней панелью, с
 * заданными цветами, отступами и нижнем пустым пространством
 *
 * @param snackbar Состояние [SnackbarHost], которое управляет очередью и текущим [snackbar],
 * отображаемым внутри [SnackbarHost].
 * @param contentColor Цвет содержимого
 * @param containerColor Цвет фона контейнера
 * @param contentWindowInsets Scaffold будет учитывать вставки top/bottom только в том случае,
 * если topBar/bottomBar отсутствуют, поскольку scaffold ожидает, что вместо этого topBar/bottomBar
 * будут обрабатывать вставки. Любые вставки, используемые другими модификаторами заполнения insets
 * или использующие WindowInsets в родительском макете, будут исключены из contentWindowInsets.
 * @param horizontalPadding Горизонтальное отступы для контента
 * @param bottomSpacing Пустое пространство снизу
 * @param bottomBar Компонент [NavigationBar] для отображения нижней панели
 * @param topBar Компонент [TopAppBar] для отображения верхней панели
 * @param floatingActionButton Компонент FAB
 * @param floatingActionButtonPosition Позиция компонента FAB
 * @param content Основное содержимое
 */
@Composable
fun Screen(
    modifier: Modifier = Modifier,
    scaffoldModifier: Modifier = Modifier,
    snackbarModifier: Modifier = Modifier,
    snackbar: SnackbarHostState = LocalSnackbar.current,
    contentColor: Color = Theme.colors.text2,
    containerColor: Color = Theme.colors.backgroundMain,
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    horizontalPadding: Dp = Theme.dimens.padding,
    bottomSpacing: Dp = 35.dp,
    bottomBar: @Composable () -> Unit = { BottomBar() },
    topBar: @Composable () -> Unit = { MainTopBar() },
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    content: @Composable ColumnScope.(PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = scaffoldModifier
            .then(globalModifier.value)
            .fillMaxSize(),
        contentColor = contentColor,
        containerColor = containerColor,
        contentWindowInsets = contentWindowInsets,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbar,
                modifier = snackbarModifier.imePadding(),
            )
        },
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        bottomBar = bottomBar,
        topBar = topBar,
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(horizontal = horizontalPadding),
        ) {
            content(innerPadding)
            Spacer(Modifier.height(bottomSpacing))
        }
    }
}

/**
 * Глобальные стили применяемые к текущей странице, на момент создания нужны только для блюра BottomSheet
 * !! Крайне не рекомендуется использовать !!
 * */
val globalModifier: MutableState<Modifier> = mutableStateOf(Modifier)
