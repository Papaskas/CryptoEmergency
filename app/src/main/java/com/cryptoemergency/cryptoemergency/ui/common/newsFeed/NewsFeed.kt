package com.cryptoemergency.cryptoemergency.ui.common.newsFeed

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cryptoemergency.cryptoemergency.providers.locale.Lang
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
    newsItemType: MutableState<NewsItemType>,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    locale: Lang = LocalLocale.current,
    selectedFilter: MutableState<String> = mutableStateOf(locale.filter.all),
    showFilterMenu: MutableState<Boolean> = mutableStateOf(false),
) {
    LazyColumn(
        state = state,
        modifier = modifier,
        contentPadding = PaddingValues(0.dp),
        verticalArrangement = Arrangement.spacedBy(
            if (newsItemType.value == NewsItemType.FULL) {
                Theme.values.padding
            } else {
                0.dp
            },
        ),
    ) {
        items(1000000000) { index ->
            if (newsItemType.value == NewsItemType.FULL) {
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
                val isEven = index % 2 == 0

                when (isEven) {
                    true -> NewsTemplate(items)
                    false -> ReverseNewsTemplate(items)
                }
            }
        }
    }

    FilterBottomSheet(selectedFilter = selectedFilter, showBottomSheet = showFilterMenu)
}

@Composable
private fun NewsTemplate(
    items: Array<NewsFeedItemProps>,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column {
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
}

@Composable
private fun ReverseNewsTemplate(
    items: Array<NewsFeedItemProps>,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column {
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
