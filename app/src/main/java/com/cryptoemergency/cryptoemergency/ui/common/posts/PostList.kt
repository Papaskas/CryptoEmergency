package com.cryptoemergency.cryptoemergency.ui.common.posts

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.models.PostViewModel
import com.papaska.domain.entity.remote.post.PostsEntity

/**
 * Компонент списка постов, панелью управления и табами
 *
 * @param posts Список постов
 * @param postViewModel Тип вывода постов
 * @param selectedFilter Выбранный фильтр вывода новостей
 * @param showFilterMenu Состояние показа экрана фильтра
 **/
@Composable
fun PostList(
    posts: PostsEntity,
    postViewModel: MutableState<PostViewModel>,
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
            if (postViewModel.value == PostViewModel.FULL) {
                Theme.dimens.padding
            } else {
                0.dp
            },
        ),
    ) {
        if (postViewModel.value == PostViewModel.FULL) {
            items(posts.postAll.size) { index ->
                PostItem(
                    post = posts.postAll[index],
                    viewType = postViewModel.value,
                )
            }
        } else {
            itemsIndexed(posts.postAll.chunked(8)) { index, chunk ->
                val isEven = index % 2 == 0

                when (isEven) {
                    true -> PostGrid(chunk)
                    false -> PostGridReverse(chunk)
                }
            }
        }
    }

    FilterBottomSheet(
        selectedFilter = selectedFilter,
        showBottomSheet = showFilterMenu,
    )
}
