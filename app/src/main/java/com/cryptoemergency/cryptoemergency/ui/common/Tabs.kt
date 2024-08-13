package com.cryptoemergency.cryptoemergency.ui.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 *
 * @param tabTitles Заголовки верхней панели
 *
 *  Пример: tabTitles = arrayOf("Title1", "Title2")
 *
 * @param content Внутренний контент
 *
 * Пример: val content = arrayOf({ Screen1() }, { Screen2() })
 *
 * @param defaultTab Выбранный заголовок по умолчанию
 *
 * */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(
    tabTitles: Array<String>,
    content: Array<@Composable () -> Unit>,
    defaultTab: Int = 0,
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
        Surface(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(horizontal = Theme.shapes.padding),
            color = Theme.colors.surface,
        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = Theme.colors.surface,
                modifier = Modifier
                    .height(35.dp)
                    .padding(4.dp),
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator( // TODO: узнать почему устарел
                        Modifier
                            .zIndex(-1f)
                            .tabIndicatorOffset(tabPositions[selectedTabIndex])
                            .height(28.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        color = Color(0xFF494949)
                    )
                },
                divider = {}, // Remove default style
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selectedContentColor = Color.Transparent,
                        selected = selectedTabIndex == index,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .height(28.dp),
                        text = {
                            Text(
                                text = title,
                                fontSize = 12.sp,
                            )
                        }
                    )
                }
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            content[index]()
        }
    }
}
