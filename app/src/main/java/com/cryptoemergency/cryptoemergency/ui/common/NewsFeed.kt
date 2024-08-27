package com.cryptoemergency.cryptoemergency.ui.common

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
import com.cryptoemergency.cryptoemergency.model.NewsFeedItemProps
import com.cryptoemergency.cryptoemergency.model.NewsItemType
import com.cryptoemergency.cryptoemergency.providers.theme.Theme

/**
 * Компонент списка новостей,панелью управления и табами
 **/
@Composable
fun NewsFeed(
    items: Array<NewsFeedItemProps>,
) {
    val showNewsFeedType = remember { mutableStateOf(NewsItemType.FULL) }
    val columnCount = remember { mutableIntStateOf(1) }

    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxSize(),
        columns = StaggeredGridCells.Fixed(columnCount.intValue),
        verticalItemSpacing = if(showNewsFeedType.value == NewsItemType.FULL) Theme.values.padding else 0.dp, // Отступы только для FULL
    ) {
        item(span = StaggeredGridItemSpan.FullLine) { // Заголовок
            Title(
                showNewsFeedType,
                columnCount,
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
}

@Composable
private fun Title(
    showNewsFeedType: MutableState<NewsItemType>,
    columnCount: MutableIntState,
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
        IconButton(onClick = { /*TODO*/ }) {
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
                tint = if(showNewsFeedType.value == NewsItemType.FULL)
                    Theme.colors.accent else Theme.colors.text1,
            )
        }
        IconButton(onClick = {
            columnCount.intValue = 3
            showNewsFeedType.value = NewsItemType.SHORT
        }) {
            Icon(
                painter = painterResource(R.drawable.sort_by_grid),
                contentDescription = null,
                tint = if(showNewsFeedType.value == NewsItemType.SHORT)
                    Theme.colors.accent else Theme.colors.text1,
            )
        }
    }
}
