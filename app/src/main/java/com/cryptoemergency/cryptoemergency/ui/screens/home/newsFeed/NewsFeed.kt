package com.cryptoemergency.cryptoemergency.ui.screens.home.newsFeed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.providers.locale.LocalLocale
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.types.NewsItemType
import com.cryptoemergency.cryptoemergency.ui.common.CommonTabs
import com.cryptoemergency.cryptoemergency.ui.common.Screen
import com.cryptoemergency.cryptoemergency.ui.common.bottomBar.BottomBar
import com.cryptoemergency.cryptoemergency.ui.common.newsFeed.NewsFeed
import com.cryptoemergency.cryptoemergency.ui.common.newsFeed.NewsFeedHeader
import com.cryptoemergency.cryptoemergency.ui.common.topBar.MainTopBar
import kotlin.math.roundToInt

@Composable
fun NewsFeedScreen(
    viewModel: NewsFeedViewModel = hiltViewModel()
) {
    val locale = LocalLocale.current
    val showFilterMenu = remember { mutableStateOf(false) }
    val newsItemType = remember { mutableStateOf(NewsItemType.FULL) }

    val toUpHeight = 110.dp // На сколько поднимать
    val barsHeightPx = with(LocalDensity.current) { toUpHeight.roundToPx().toFloat() }
    val barsOffsetHeightPx = remember { mutableFloatStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = barsOffsetHeightPx.floatValue + delta
                barsOffsetHeightPx.floatValue = newOffset.coerceIn(-barsHeightPx, 0f)
                return Offset.Zero
            }
        }
    }

    val bottomPadding by remember {
        derivedStateOf { (toUpHeight + barsOffsetHeightPx.floatValue.dp).coerceAtLeast(0.dp) }
    }

    val topPadding by remember {
        derivedStateOf { (toUpHeight + barsOffsetHeightPx.floatValue.dp).coerceAtLeast(25.dp) }
    }

    Screen(
        topBar = {
            MainTopBar(
                Modifier.offset { IntOffset(x = 0, y = barsOffsetHeightPx.floatValue.roundToInt()) },
            )
        },
        bottomBar = {
            BottomBar(
                Modifier.offset { IntOffset(x = 0, y = -(barsOffsetHeightPx.floatValue.roundToInt())) },
            )
        },
        scaffoldModifier = Modifier.nestedScroll(nestedScrollConnection),
        horizontalPadding = 0.dp,
        bottomPadding = bottomPadding,
        topPadding = topPadding,
        boxModifier = Modifier.padding(0.dp)
    ) {
        Surface(
            color = Theme.colors.surface1,
            shape = RoundedCornerShape(
                topStart = Theme.values.shape,
                topEnd = Theme.values.shape,
            ),
        ) {
            CommonTabs(
                tabTitles = arrayOf(
                    locale.tabs.generalFeed,
                    locale.tabs.subscriptionFeed,
                    locale.tabs.allCems,
                    locale.tabs.cemsSubscriptions,
                    locale.tabs.subscriptionFeed,
                    locale.tabs.socialNetworks,
                    locale.tabs.bookmarks,
                ),
            ) { _ ->
                Column {
                    NewsFeedHeader(
                        newsItemType = newsItemType,
                        showFilterMenu = showFilterMenu,
                    )

                    NewsFeed(
                        items = viewModel.items,
                        newsItemType = newsItemType,
                    )
                }
            }
        }
    }
}
