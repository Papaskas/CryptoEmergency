package com.cryptoemergency.cryptoemergency.ui.screens.home.newsFeed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.lib.Listener
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
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

    Listener(viewModel.message)

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
            } ?:run {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = Theme.colors.accent,
                        modifier = Modifier.offset(y = -105.dp) // height bottom bar
                    )
                }
            }
        }
    }
}
