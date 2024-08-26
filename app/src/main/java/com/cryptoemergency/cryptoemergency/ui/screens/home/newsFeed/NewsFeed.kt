package com.cryptoemergency.cryptoemergency.ui.screens.home.newsFeed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Text
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.model.NewsFeedItemProps
import com.cryptoemergency.cryptoemergency.model.NewsItemType
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.CommonTabs
import com.cryptoemergency.cryptoemergency.ui.common.NewsFeedItem
import com.cryptoemergency.cryptoemergency.ui.common.ScrollableScreen

@Composable
fun NewsFeedScreen(
    viewModel: NewsFeedViewModel = hiltViewModel()
) {
    Box(
        Modifier
            .background(
                color = Theme.colors.surface1,
                shape = RoundedCornerShape(10.dp),
            )
            .fillMaxSize()
    ) {
        CommonTabs(
            tabTitles = arrayOf("Общая лента", "Лента подписок", "Общие Cems", "Cems"),
        ) { _ ->
            Content(viewModel)
        }
    }
}

@Composable
private fun Content(
    viewModel: NewsFeedViewModel,
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxSize(),
        columns = StaggeredGridCells.Fixed(viewModel.columCount.value),
        verticalItemSpacing = if(viewModel.showNewsFeedType.value == NewsItemType.FULL) Theme.values.padding else 0.dp
    ) {
        item(span = StaggeredGridItemSpan.FullLine){
            Title(viewModel)
        }
        items(viewModel.items.size) { index ->
            NewsFeedItem(
                NewsFeedItemProps(
                    media = viewModel.items[index].media,
                    avatar = viewModel.items[index].avatar,
                    authorName = viewModel.items[index].authorName,
                    createDate = viewModel.items[index].createDate,
                    type = viewModel.showNewsFeedType.value,
                    description = viewModel.items[index].description,
                )
            )
        }
    }
}

@Composable
private fun Title(
    viewModel: NewsFeedViewModel,
) {
    Row(
        modifier = Modifier.padding(Theme.values.padding),
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
            viewModel.columCount.value = 1
            viewModel.showNewsFeedType.value = NewsItemType.FULL
        }) {
            Icon(
                painter = painterResource(R.drawable.sort_by_large),
                contentDescription = null,
                tint = if(viewModel.showNewsFeedType.value == NewsItemType.FULL)
                    Theme.colors.accent else Theme.colors.text1,
            )
        }
        IconButton(onClick = {
            viewModel.columCount.value = 3
            viewModel.showNewsFeedType.value = NewsItemType.SHORT
        }) {
            Icon(
                painter = painterResource(R.drawable.sort_by_grid),
                contentDescription = null,
                tint = if(viewModel.showNewsFeedType.value == NewsItemType.SHORT)
                    Theme.colors.accent else Theme.colors.text1,
            )
        }
    }
}
