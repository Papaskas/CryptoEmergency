package com.cryptoemergency.cryptoemergency.ui.common

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.IntState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.providers.theme.Theme

/**
 * Множество экранов появляющихся по этапно
 *
 * @param screens Экраны появляющиеся по этапно. Принимают переменную изменение которой приводит к
 * другому экрану
 * @param steps Перменная изменение которой приводит к переходу к следующему этапу
 * @param pageSpacing Промежуток между экранами. Не влияет на верстку
 **/
@Composable
fun SteppedScreen(
    steps: IntState,
    screens: List<@Composable () -> Unit>,
    initialPage: Int = 0,
    modifier: Modifier = Modifier,
    animationSpec: AnimationSpec<Float> = tween(durationMillis = 300),
    pageSpacing: Dp = 15.dp,
) {
    val state = rememberPagerState(
        initialPage = initialPage,
    ) { screens.size }

    LaunchedEffect(steps.intValue) {
        state.animateScrollToPage(
            page = steps.intValue,
            animationSpec = animationSpec,
        )
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
