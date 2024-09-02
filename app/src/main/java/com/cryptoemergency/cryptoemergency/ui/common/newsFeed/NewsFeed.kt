package com.cryptoemergency.cryptoemergency.ui.common.newsFeed

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.types.NewsFeedItemProps
import com.cryptoemergency.cryptoemergency.types.NewsItemType

/**
 * Компонент списка новостей, панелью управления и табами
 **/
@Composable
fun NewsFeed(
    items: Array<NewsFeedItemProps>,
) {
    val showNewsFeedType = remember { mutableStateOf(NewsItemType.FULL) }
    val showFilterMenu = remember { mutableStateOf(false) }
    val selectedFilter = remember { mutableStateOf("Все") }
    val columnCount = remember { mutableIntStateOf(1) }

    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxSize(),
        columns = StaggeredGridCells.Fixed(columnCount.intValue),
        verticalItemSpacing = if (showNewsFeedType.value == NewsItemType.FULL) {
            Theme.values.padding
        } else {
            0.dp
        }, // Отступы только для NewsItemType.FULL
    ) {
        item(span = StaggeredGridItemSpan.FullLine) { // Заголовок
            Header(
                showNewsFeedType,
                columnCount,
                showFilterMenu,
            )
        }
        items(items.size) { index ->
            NewsFeedItem(
                NewsFeedItemProps(
                    media = items[index].media,
                    avatar = items[index].avatar,
                    authorName = items[index].authorName,
                    createDate = items[index].createDate,
                    type = showNewsFeedType.value,
                    description = items[index].description,
                )
            )
        }
    }

    FilterBottomSheet(selectedFilter = selectedFilter, showBottomSheet = showFilterMenu)
}

@Composable
private fun Header(
    showNewsFeedType: MutableState<NewsItemType>,
    columnCount: MutableIntState,
    showFilterMenu: MutableState<Boolean>
) {
    Row(
        modifier = Modifier.padding(horizontal = Theme.values.padding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Лента",
            style = Theme.typography.h2,
            color = Theme.colors.text1,
        )
        Spacer(Modifier.weight(1f))
        IconButton(onClick = { showFilterMenu.value = true }) {
            Icon(
                painter = painterResource(R.drawable.filter),
                contentDescription = null,
            )
        }
        IconButton(onClick = {
            columnCount.intValue = 1
            showNewsFeedType.value = NewsItemType.FULL
        }) {
            Icon(
                painter = painterResource(R.drawable.sort_by_large),
                contentDescription = null,
                tint = if (showNewsFeedType.value == NewsItemType.FULL) {
                    Theme.colors.accent
                } else {
                    Theme.colors.text1
                },
            )
        }
        IconButton(onClick = {
            columnCount.intValue = 3
            showNewsFeedType.value = NewsItemType.SHORT
        }) {
            Icon(
                painter = painterResource(R.drawable.sort_by_grid),
                contentDescription = null,
                tint = if (showNewsFeedType.value == NewsItemType.SHORT) {
                    Theme.colors.accent
                } else {
                    Theme.colors.text1
                },
            )
        }
    }
}
