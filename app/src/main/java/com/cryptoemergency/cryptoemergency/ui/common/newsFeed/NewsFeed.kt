package com.cryptoemergency.cryptoemergency.ui.common.newsFeed

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import coil.compose.AsyncImage
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.locale.LocalLocale
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
    val locale = LocalLocale.current
    val newsFeedType = remember { mutableStateOf(NewsItemType.FULL) }
    val showFilterMenu = remember { mutableStateOf(false) }
    val selectedFilter = remember { mutableStateOf(locale.filter.all) }
    val columnCount = remember { mutableIntStateOf(1) }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(1),
        verticalItemSpacing = if (newsFeedType.value == NewsItemType.FULL) {
            Theme.values.padding
        } else {
            0.dp
        }, // Отступы только для NewsItemType.FULL
    ) {
        item(span = StaggeredGridItemSpan.FullLine) { // Заголовок
            Header(
                newsFeedType,
                columnCount,
                showFilterMenu,
            )
        }
        items(1000000000) { index ->
            val span = when {
                index % 4 == 0 -> 4
                index % 3 == 0 -> 3
                index % 2 == 0 -> 2
                else -> 1
            }

            if (newsFeedType.value == NewsItemType.FULL) {
                NewsFeedItem(
                    props = NewsFeedItemProps(
                        media = items[1].media,
                        authorName = items[1].authorName,
                        avatar = items[1].avatar,
                        createDate = items[1].createDate,
                        type = items[1].type,
                        description = items[1].description,
                    )
                )
            } else {
                when (span) {
                    1 -> {
                        Row {
                            Row {
                                Column {
                                    Temp(
                                        media = items[0].media[0],
                                        width = screenWidth / 3,
                                    )
                                    Temp(
                                        media = items[0].media[0],
                                        width = screenWidth / 3,
                                    )
                                }
                                Column {
                                    Temp(
                                        media = items[0].media[0],
                                        width = screenWidth / 3,
                                    )
                                    Temp(
                                        media = items[0].media[0],
                                        width = screenWidth / 3,
                                    )
                                }
                            }
                            Temp(
                                media = items[0].media[0],
                                width = screenWidth / 3,
                                height = screenWidth / 1.5f,
                            )
                        }
                    }
                    2 -> {
                        Row {
                            Column {
                                Temp(
                                    media = items[0].media[0],
                                    width = screenWidth / 3,
                                )
                                Temp(
                                    media = items[0].media[0],
                                    width = screenWidth / 3,
                                )
                            }
                            Temp(
                                media = items[0].media[0],
                                width = screenWidth / 1.5f,
                            )
                        }
                    }
                    3 -> {
                        Row {
                            Row {
                                Temp(
                                    media = items[0].media[0],
                                    width = screenWidth / 3,
                                    height = screenWidth / 1.5f,
                                )
                                Column {
                                    Temp(
                                        media = items[0].media[0],
                                        width = screenWidth / 3,
                                    )
                                    Temp(
                                        media = items[0].media[0],
                                        width = screenWidth / 3,
                                    )
                                }
                                Column {
                                    Temp(
                                        media = items[0].media[0],
                                        width = screenWidth / 3,
                                    )
                                    Temp(
                                        media = items[0].media[0],
                                        width = screenWidth / 3,
                                    )
                                }
                            }
                        }
                    }
                    4 -> {
                        Row {
                            Temp(
                                media = items[0].media[0],
                                width = screenWidth / 1.5f,
                            )
                            Column {
                                Temp(
                                    media = items[0].media[0],
                                    width = screenWidth / 3,
                                )
                                Temp(
                                    media = items[0].media[0],
                                    width = screenWidth / 3,
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    FilterBottomSheet(selectedFilter = selectedFilter, showBottomSheet = showFilterMenu)
}

@Composable
private fun Temp(
    media: Uri,
    width: Dp,
    height: Dp = width,
) {
    AsyncImage(
        model = media,
        contentDescription = null,
        modifier = Modifier.size(
            width = width,
            height = height,
        ),
        contentScale = ContentScale.Crop,
    )
}

@Composable
private fun Header(
    showNewsFeedType: MutableState<NewsItemType>,
    columnCount: MutableIntState,
    showFilterMenu: MutableState<Boolean>
) {
    val locale = LocalLocale.current.newsFeedSection

    Row(
        modifier = Modifier.padding(horizontal = Theme.values.padding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = locale.myFeed,
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
            columnCount.intValue = 1
            showNewsFeedType.value = NewsItemType.FULL
        }) {
            Icon(
                painter = painterResource(R.drawable.sort_by_large),
                contentDescription = "sort by large",
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
                contentDescription = "sort by grid",
                tint = if (showNewsFeedType.value == NewsItemType.SHORT) {
                    Theme.colors.accent
                } else {
                    Theme.colors.text1
                },
            )
        }
    }
}
