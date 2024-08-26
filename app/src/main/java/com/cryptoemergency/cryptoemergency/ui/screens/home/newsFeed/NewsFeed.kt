package com.cryptoemergency.cryptoemergency.ui.screens.home.newsFeed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
        ) { index ->
            Content(index, viewModel)
        }
    }
}

@Composable
private fun Title() {
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
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(R.drawable.sort_by_large),
                contentDescription = null,
                tint = Theme.colors.accent,
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(R.drawable.sort_by_grid),
                contentDescription = null,
            )
        }
    }
}

@Composable
private fun Content(
    index: Int,
    viewModel: NewsFeedViewModel,
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(1),
    ) {
        item {
            Title()
        }
        items(viewModel.items.size) { index ->
            Column {
                NewsFeedItem(viewModel.items[index])
                Spacer(Modifier.height(Theme.values.padding))
            }
        }
    }
}
