package com.cryptoemergency.cryptoemergency.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Компонент вкладок с горизонтальным перелистыванием страниц.
 *
 * @param modifierTabRow Модификатор для строки вкладок.
 * @param modifierTab Модификатор для каждой вкладки.
 * @param tabTitles Массив заголовков вкладок.
 * @param defaultTab Индекс вкладки по умолчанию.
 * @param content Композиционная функция для отображения контента на каждой странице.
 *
 * @sample SampleTabs
 * @sample SampleTabsWithList
 * @sample SampleTabsWithComposableFunc
 */
@Composable
fun CommonTabs(
    modifierTabRow: Modifier = Modifier,
    modifierTab: Modifier = Modifier,
    tabTitles: Array<String>,
    defaultTab: Int = 0,
    content: @Composable PagerScope.(Int) -> Unit,
) {
    var selectedTabIndex by remember { mutableIntStateOf(defaultTab) }
    val pagerState = rememberPagerState { tabTitles.size }
    val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collectLatest { page ->
            selectedTabIndex = page
        }
    }

    Column {
        Column(
            Modifier.padding(horizontal = Theme.values.padding)
        ) {
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = Color.Transparent,
                contentColor = Theme.colors.accent,
                edgePadding = 0.dp,
                divider = {},
                modifier = modifierTabRow.fillMaxWidth(),
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        modifier = modifierTab,
                        unselectedContentColor = Theme.colors.text2,
                        selectedContentColor = Theme.colors.accent,
                        selected = selectedTabIndex == index,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = {
                            Text(
                                text = title,
                                style = Theme.typography.caption1,
                            )
                        }
                    )
                }
            }
            CommonHorizontalDivider(
                modifier = Modifier
                    .offset(y = (-2).dp)
                    .zIndex(-1f),
                thickness = 2.dp
            )
        }

        HorizontalPager(
            state = pagerState,
        ) { index ->
            content(index)
        }
    }
}

@Composable
private fun SampleTabs() {
    CommonTabs(
        tabTitles = arrayOf("Tab 1", "Tab 2", "Tab 3"),
    ) { index ->
        Text(text = "Page $index")
    }
}

@Composable
private fun SampleTabsWithList() {
    val items = listOf("asdasd", "ASdasd")

    CommonTabs(
        tabTitles = arrayOf("Tab 1", "Tab 2", "Tab 3"),
    ) { index ->
        Text(text = items[index])
    }
}

@Composable
private fun SampleTabsWithComposableFunc() {
    val items = listOf<@Composable () -> Unit>(
        { Text("AAAAA") },
        { Text("BBBB") },
    )

    CommonTabs(
        tabTitles = arrayOf("Tab 1", "Tab 2", "Tab 3"),
    ) { index ->
        items[index]
    }
}
