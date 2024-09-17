package com.cryptoemergency.cryptoemergency.ui.screens.home.newsFeed

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.types.PostViewType
import com.cryptoemergency.cryptoemergency.ui.common.FullScreen
import com.cryptoemergency.cryptoemergency.ui.common.posts.PostList
import com.cryptoemergency.cryptoemergency.ui.common.posts.PostsHeader

@Composable
fun NewsFeedScreen(
    viewModel: NewsFeedViewModel = hiltViewModel()
) {
    val showFilterMenu = remember { mutableStateOf(false) }
    val postViewType = remember { mutableStateOf(PostViewType.FULL) }

    FullScreen {
        Column {
            PostsHeader(
                postViewType = postViewType,
                showFilterMenu = showFilterMenu,
            )

            val posts = viewModel.posts.collectAsState().value

            posts?.let {
                PostList(
                    showFilterMenu = showFilterMenu,
                    posts = posts,
                    postViewType = postViewType,
                )
            }
        }
    }
}
