package com.cryptoemergency.cryptoemergency.ui.common.newsFeed

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.wear.compose.material.Text
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.types.NewsItemType

@Composable
fun NewsFeedHeader(
    newsItemType: MutableState<NewsItemType>,
    showFilterMenu: MutableState<Boolean>,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Row(
        modifier = modifier.padding(horizontal = Theme.dimens.padding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = context.getString(R.string.my_feed),
            style = Theme.typography.h2,
            color = Theme.colors.text1,
        )
        Spacer(Modifier.weight(1f))
        IconButton(onClick = { showFilterMenu.value = true }) {
            Icon(
                painter = painterResource(R.drawable.filter),
                contentDescription = "filter",
            )
        }
        IconButton(onClick = {
            newsItemType.value = NewsItemType.FULL
        }) {
            Icon(
                painter = painterResource(R.drawable.sort_by_large),
                contentDescription = "sort by large",
                tint = if (newsItemType.value == NewsItemType.FULL) {
                    Theme.colors.accent
                } else {
                    Theme.colors.text1
                },
            )
        }
        IconButton(onClick = {
            newsItemType.value = NewsItemType.SHORT
        }) {
            Icon(
                painter = painterResource(R.drawable.sort_by_grid),
                contentDescription = "sort by grid",
                tint = if (newsItemType.value == NewsItemType.SHORT) {
                    Theme.colors.accent
                } else {
                    Theme.colors.text1
                },
            )
        }
    }
}
