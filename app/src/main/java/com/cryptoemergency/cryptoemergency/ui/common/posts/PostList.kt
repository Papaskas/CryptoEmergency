package com.cryptoemergency.cryptoemergency.ui.common.posts

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.repository.requests.getPosts.Post
import com.cryptoemergency.cryptoemergency.repository.requests.getPosts.PostsResponse
import com.cryptoemergency.cryptoemergency.types.PostViewType

/**
 * Компонент списка постов, панелью управления и табами
 *
 * @param posts Список постов
 * @param postViewType Тип вывода постов
 * @param selectedFilter Выбранный фильтр вывода новостей
 * @param showFilterMenu Состояние показа экрана фильтра
 **/
@Composable
fun PostList(
    posts: PostsResponse,
    postViewType: MutableState<PostViewType>,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    context: Context = LocalContext.current,
    selectedFilter: MutableState<String> = mutableStateOf(context.getString(R.string.general_feed)),
    showFilterMenu: MutableState<Boolean> = mutableStateOf(false),
) {
    LazyColumn(
        state = state,
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(0.dp),
        verticalArrangement = Arrangement.spacedBy(
            if (postViewType.value == PostViewType.FULL) {
                Theme.dimens.padding
            } else {
                0.dp
            },
        ),
    ) {
        if (postViewType.value == PostViewType.FULL) {
            items(posts.postAll.size) { index ->
                PostItem(
                    post = posts.postAll[index],
                    viewType = postViewType.value,
                )
            }
        } else {
            // TODO: delete this, it's a temp
            val deleteThisVar = posts.postAll + posts.postAll + posts.postAll + posts.postAll + posts.postAll + posts.postAll + posts.postAll + posts.postAll

            itemsIndexed(deleteThisVar.chunked(8)) { index, chunk ->
                val isEven = index % 2 == 0

                when (isEven) {
                    true -> PostCommonTemplate(chunk)
                    false -> PostReverseTemplate(chunk)
                }
            }
        }
    }

    FilterBottomSheet(
        selectedFilter = selectedFilter,
        showBottomSheet = showFilterMenu,
    )
}

@Composable
private fun PostCommonTemplate(
    posts: List<Post>,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column {
        Row {
            Row {
                PostItem(
                    post = posts[0],
                    viewType = PostViewType.SHORT,
                    mediaModifier = Modifier.size(
                        width = screenWidth / 3,
                        height = screenWidth / 1.5f,
                    ),
                )
                Column {
                    PostItem(
                        post = posts[1],
                        viewType = PostViewType.SHORT,
                        mediaModifier = Modifier.size(screenWidth / 3),
                    )
                    PostItem(
                        post = posts[2],
                        viewType = PostViewType.SHORT,
                        mediaModifier = Modifier.size(screenWidth / 3),
                    )
                }
                Column {
                    PostItem(
                        post = posts[3],
                        viewType = PostViewType.SHORT,
                        mediaModifier = Modifier.size(screenWidth / 3),
                    )
                    PostItem(
                        post = posts[4],
                        viewType = PostViewType.SHORT,
                        mediaModifier = Modifier.size(screenWidth / 3),
                    )
                }
            }
        }

        Row {
            Column {
                PostItem(
                    post = posts[5],
                    viewType = PostViewType.SHORT,
                    mediaModifier = Modifier.size(screenWidth / 3),
                )
                PostItem(
                    post = posts[6],
                    viewType = PostViewType.SHORT,
                    mediaModifier = Modifier.size(screenWidth / 3),
                )
            }
            PostItem(
                post = posts[7],
                viewType = PostViewType.SHORT,
                mediaModifier = Modifier.size(screenWidth / 1.5f),
            )
        }
    }
}

@Composable
private fun PostReverseTemplate(
    posts: List<Post>,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column {
        Row {
            Row {
                Column {
                    PostItem(
                        post = posts[0],
                        viewType = PostViewType.SHORT,
                        mediaModifier = Modifier.size(screenWidth / 3),
                    )
                    PostItem(
                        post = posts[1],
                        viewType = PostViewType.SHORT,
                        mediaModifier = Modifier.size(screenWidth / 3),
                    )
                }
                Column {
                    PostItem(
                        post = posts[2],
                        viewType = PostViewType.SHORT,
                        mediaModifier = Modifier.size(screenWidth / 3),
                    )
                    PostItem(
                        post = posts[3],
                        viewType = PostViewType.SHORT,
                        mediaModifier = Modifier.size(screenWidth / 3),
                    )
                }
            }
            PostItem(
                post = posts[4],
                viewType = PostViewType.SHORT,
                mediaModifier = Modifier.size(
                    width = screenWidth / 3,
                    height = screenWidth / 1.5f,
                ),
            )
        }

        Row {
            PostItem(
                post = posts[5],
                viewType = PostViewType.SHORT,
                mediaModifier = Modifier.size(screenWidth / 1.5f),
            )
            Column {
                PostItem(
                    post = posts[6],
                    viewType = PostViewType.SHORT,
                    mediaModifier = Modifier.size(screenWidth / 3),
                )
                PostItem(
                    post = posts[7],
                    viewType = PostViewType.SHORT,
                    mediaModifier = Modifier.size(screenWidth / 3),
                )
            }
        }
    }
}
