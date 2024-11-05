package com.cryptoemergency.cryptoemergency.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.providers.theme.Theme

/**
 * Множество экранов появляющихся по этапно
 *
 * @param screens Экраны появляющиеся по этапно. Принимают переменную изменение которой приводит к
 * другому экрану
 * @param currentStep Перменная изменение которой приводит к переходу к нужному этапу
 * @param pageSpacing Промежуток между экранами. Не влияет на верстку
 * @param content Необязательная обертка над всеми страницами
 *
 * @sample Sample
 **/
@Composable
fun SteppedScreen(
    currentStep: MutableIntState,
    screens: List<@Composable () -> Unit>,
    modifier: Modifier = Modifier,
    pageSpacing: Dp = 15.dp,
    content: @Composable ((content: @Composable () -> Unit) -> Unit)? = null,
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
        content?.let {
            it {
                screens[page]()
            }
        } ?: run {
            screens[page]()
        }
    }
}

@Composable
private fun Sample() {
    val currentStep = remember { mutableIntStateOf(0) }

    val screens = listOf<@Composable () -> Unit>(
        { FirstStep(currentStep) },
        { SecondStep(currentStep) },
        { ThirdStep(currentStep) },
    )

    SteppedScreen(currentStep, screens) {
        Screen(
            bottomBar = {},
            topBar = {},
        ) {
            it()
        }
    }
}

@Composable
private fun FirstStep(currentStep: MutableIntState) {}

@Composable
private fun SecondStep(currentStep: MutableIntState) {}

@Composable
private fun ThirdStep(currentStep: MutableIntState) {}