package com.cryptoemergency.cryptoemergency.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.providers.localSnackBar.LocalSnackbar
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.bottomBar.BottomBar
import com.cryptoemergency.cryptoemergency.ui.common.topBar.MainTopBar

/**
 * Компонент с настроенной начальной логикой отображения старницы. С верхней и нижней панелью, с
 * заданными цветами, отступами, нижнем пустым пространством
 *
 * @param snackbar Состояние [SnackbarHost], которое управляет очередью и текущим [snackbar],
 * отображаемым внутри [SnackbarHost].
 * @param contentColor Цвет содержимого
 * @param containerColor Цвет фона контейнера
 * @param horizontalPadding Горизонтальное отступы для контента
 * @param bottomSpacing Пустое пространство снизу
 * @param bottomBar Компонент [NavigationBar] для отображения нижней панели
 * @param topBar Компонент [TopAppBar] для отображения верхней панели
 * @param content Компонуемая функция для отображения основного содержимого внутри [BoxScope].
 */
@Composable
fun Screen(
    modifier: Modifier = Modifier,
    snackbar: SnackbarHostState = LocalSnackbar.current,
    contentColor: Color = Theme.colors.text2,
    containerColor: Color = Theme.colors.backgroundMain,
    horizontalPadding: PaddingValues = PaddingValues(horizontal = Theme.values.padding),
    bottomSpacing: Dp = 35.dp,
    bottomBar: @Composable () -> Unit = { BottomBar() },
    topBar: @Composable () -> Unit = { MainTopBar() },
    content: @Composable ColumnScope.() -> Unit,
) {
    Scaffold(
        contentColor = contentColor,
        containerColor = containerColor,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbar,
                modifier = Modifier.imePadding(),
            )
        },
        bottomBar = bottomBar,
        topBar = topBar,
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding(),
                )
                .padding(horizontalPadding),
        ) {
            Column(
                modifier = modifier,
            ) {
                content()
                Spacer(Modifier.height(bottomSpacing))
            }
        }
    }
}
