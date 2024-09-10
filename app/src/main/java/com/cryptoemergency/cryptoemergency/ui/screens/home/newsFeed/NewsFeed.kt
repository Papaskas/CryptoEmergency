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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.types.NewsItemType
import com.cryptoemergency.cryptoemergency.ui.common.CommonTabs
import com.cryptoemergency.cryptoemergency.ui.common.Screen
import com.cryptoemergency.cryptoemergency.ui.common.bottomBar.BottomBar
import com.cryptoemergency.cryptoemergency.ui.common.newsFeed.NewsFeed
import com.cryptoemergency.cryptoemergency.ui.common.newsFeed.NewsFeedHeader
import com.cryptoemergency.cryptoemergency.ui.common.topBar.MainTopBar
import kotlin.math.roundToInt

/**
 * Как работает скрытие элементов
 *
 * 1) TopBar & BottomBar не имеют [padding], что значит элементы под них проваливаются
 * 2) Когда начинается движение, они с помощью [offset] уходят за края
 * 3) Поскольку динамическое изменение [padding] тормознутое, то отстут от верха утановлен с помощью [offset]
 * и переменной [topBarOffset] заданный в [Surface]
 * 4) То есть дочерний элемент с помощью [offset] отступает только от верха, а когда происходит прокрутка
 * двигается вверх до ограничителя 35.dp - для StatusBar
 * */
@Composable
fun NewsFeedScreen(
    viewModel: NewsFeedViewModel = hiltViewModel()
) {
    val res = LocalContext.current.resources
    val showFilterMenu = remember { mutableStateOf(false) }
    val newsItemType = remember { mutableStateOf(NewsItemType.FULL) }

    val topBarHeight = 110.dp
    val topBarHeightPx = with(LocalDensity.current) { topBarHeight.roundToPx().toFloat() }
    val topBarOffsetHeightPx = remember { mutableFloatStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = topBarOffsetHeightPx.floatValue + delta
                topBarOffsetHeightPx.floatValue = newOffset.coerceIn(-topBarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }

    val topBarOffset by remember {
        derivedStateOf {
            (topBarHeight + topBarOffsetHeightPx.floatValue.dp).coerceAtLeast(35.dp) // 35.dp == StatusBar
        }
    }

    Screen(
        topBar = {
            MainTopBar(
                Modifier.offset { IntOffset(x = 0, y = topBarOffsetHeightPx.floatValue.roundToInt()) },
            )
        },
        bottomBar = {
            BottomBar(
                Modifier.offset { IntOffset(x = 0, y = -(topBarOffsetHeightPx.floatValue.roundToInt())) },
            )
        },
        scaffoldModifier = Modifier.nestedScroll(nestedScrollConnection),
        horizontalPadding = 0.dp,
    ) {
        Surface(
            modifier = Modifier.offset {
                IntOffset(
                    x = 0,
                    y = topBarOffset.roundToPx()
                )
            },
            color = Theme.colors.surface1,
            shape = RoundedCornerShape(
                topStart = Theme.dimens.shape,
                topEnd = Theme.dimens.shape,
            ),
        ) {
            CommonTabs(
                tabTitles = arrayOf(
                    res.getString(R.string.general_feed),
                    res.getString(R.string.subscription_feed),
                    res.getString(R.string.all_cems),
                    res.getString(R.string.cems_subscriptions),
                    res.getString(R.string.subscription_feed),
                    res.getQuantityString(R.plurals.social_network, 3),
                    res.getString(R.string.bookmark),
                ),
            ) { _ ->
                Column {
                    NewsFeedHeader(
                        newsItemType = newsItemType,
                        showFilterMenu = showFilterMenu,
                    )

                    NewsFeed(
                        showFilterMenu = showFilterMenu,
                        items = viewModel.items,
                        newsItemType = newsItemType,
                    )
                }
            }
        }
    }
}
