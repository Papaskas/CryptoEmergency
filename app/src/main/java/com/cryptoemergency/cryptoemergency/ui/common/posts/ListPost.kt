package com.cryptoemergency.cryptoemergency.ui.common.posts

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.repository.requests.getPosts.Media
import com.cryptoemergency.cryptoemergency.repository.requests.getPosts.Post
import com.cryptoemergency.cryptoemergency.repository.requests.getPosts.PostsResponse
import com.cryptoemergency.cryptoemergency.types.PostViewType

/**
 * Компонент списка постов, панелью управления и табами
 **/
@Composable
fun ListPost(
    posts: PostsResponse,
    postViewType: MutableState<PostViewType>,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    context: Context = LocalContext.current,
    selectedFilter: MutableState<String> = mutableStateOf(context.getString(R.string.all)),
    showFilterMenu: MutableState<Boolean> = mutableStateOf(false),
) {
    LazyColumn(
        state = state,
        modifier = modifier,
        contentPadding = PaddingValues(0.dp),
        verticalArrangement = Arrangement.spacedBy(
            if (postViewType.value == PostViewType.FULL) {
                Theme.dimens.padding
            } else {
                0.dp
            },
        ),
    ) {
        items(posts.postAll.size) { index ->
            if (postViewType.value == PostViewType.FULL) {
                PostItem(
                    post = posts.postAll[index],
                    viewType = postViewType.value,
                )
            } else {
                val isEven = index % 2 == 0

                when (isEven) {
                    true -> NewsTemplate(posts.postAll)
                    false -> ReverseNewsTemplate(posts.postAll)
                }
            }
        }
    }

    FilterBottomSheet(
        selectedFilter = selectedFilter,
        showBottomSheet = showFilterMenu
    )
}

@Composable
private fun NewsTemplate(
    posts: List<Post>,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column {
        Row {
            Row {
                Temp(
                    media = posts[0].media[0],
                    width = screenWidth / 3,
                    height = screenWidth / 1.5f,
                )
                Column {
                    Temp(
                        media = posts[0].media[0],
                        width = screenWidth / 3,
                    )
                    Temp(
                        media = posts[0].media[0],
                        width = screenWidth / 3,
                    )
                }
                Column {
                    Temp(
                        media = posts[0].media[0],
                        width = screenWidth / 3,
                    )
                    Temp(
                        media = posts[0].media[0],
                        width = screenWidth / 3,
                    )
                }
            }
        }

        Row {
            Column {
                Temp(
                    media = posts[0].media[0],
                    width = screenWidth / 3,
                )
                Temp(
                    media = posts[0].media[0],
                    width = screenWidth / 3,
                )
            }
            Temp(
                media = posts[0].media[0],
                width = screenWidth / 1.5f,
            )
        }
    }
}

@Composable
private fun ReverseNewsTemplate(
    posts: List<Post>,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column {
        Row {
            Row {
                Column {
                    Temp(
                        media = posts[0].media[0],
                        width = screenWidth / 3,
                    )
                    Temp(
                        media = posts[0].media[0],
                        width = screenWidth / 3,
                    )
                }
                Column {
                    Temp(
                        media = posts[0].media[0],
                        width = screenWidth / 3,
                    )
                    Temp(
                        media = posts[0].media[0],
                        width = screenWidth / 3,
                    )
                }
            }
            Temp(
                media = posts[0].media[0],
                width = screenWidth / 3,
                height = screenWidth / 1.5f,
            )
        }

        Row {
            Temp(
                media = posts[0].media[0],
                width = screenWidth / 1.5f,
            )
            Column {
                Temp(
                    media = posts[0].media[0],
                    width = screenWidth / 3,
                )
                Temp(
                    media = posts[0].media[0],
                    width = screenWidth / 3,
                )
            }
        }
    }
}

@Composable
private fun Temp(
    media: Media,
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
