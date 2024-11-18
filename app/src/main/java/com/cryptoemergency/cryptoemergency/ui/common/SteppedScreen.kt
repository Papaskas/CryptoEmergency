package com.cryptoemergency.cryptoemergency.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.providers.theme.Theme

/**
 * Множество экранов появляющихся по этапно
 *
 * @param screens Экраны появляющиеся по этапно. Принимают переменную изменение которой приводит к
 * другому экрану
 * @param currentStep Перменная изменение которой приводит к переходу к следующему этапу
 * @param pageSpacing Промежуток между экранами. Не влияет на верстку
 **/
@Composable
fun SteppedScreen(
    currentStep: MutableIntState,
    screens: List<@Composable () -> Unit>,
    modifier: Modifier = Modifier,
    pageSpacing: Dp = 15.dp,
) {
    val state = rememberPagerState(
        initialPage = currentStep.intValue,
    ) { screens.size }

    LaunchedEffect(currentStep.intValue) {
        state.animateScrollToPage(currentStep.intValue)
    }

    HorizontalPager(
        state = state,
        modifier = modifier
            .fillMaxSize()
            .background(Theme.colors.backgroundMain),
        userScrollEnabled = false,
        pageSpacing = pageSpacing,
    ) { page ->
        screens[page]()
    }
}
