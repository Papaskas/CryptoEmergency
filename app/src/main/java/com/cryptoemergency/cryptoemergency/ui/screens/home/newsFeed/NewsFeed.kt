package com.cryptoemergency.cryptoemergency.ui.screens.home.newsFeed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.common.BaseUiState
import com.cryptoemergency.cryptoemergency.navigation.Destination
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController
import com.cryptoemergency.cryptoemergency.providers.localSnackBar.LocalSnackBar
import com.cryptoemergency.cryptoemergency.providers.theme.provides.Theme
import com.cryptoemergency.cryptoemergency.ui.common.FullScreen
import com.cryptoemergency.cryptoemergency.ui.common.posts.PostList
import com.cryptoemergency.cryptoemergency.ui.common.posts.PostsHeader
import com.cryptoemergency.cryptoemergency.ui.screens.auth.login.UiState
import com.papaska.core.entity.remote.post.PostViewEntity

@Composable
fun NewsFeedScreen(
    viewModel: NewsFeedViewModel = hiltViewModel()
) {
    val showFilterMenu = remember { mutableStateOf(false) }
    val postViewEntity = remember { mutableStateOf(PostViewEntity.FULL) }

    HandlerUiState(viewModel.uiState.collectAsState())

    FullScreen {
        Column {
            PostsHeader(
                postViewEntity = postViewEntity,
                showFilterMenu = showFilterMenu,
            )

            val posts = viewModel.posts.collectAsState().value

            posts?.let {
                PostList(
                    showFilterMenu = showFilterMenu,
                    posts = posts,
                    postViewEntity = postViewEntity,
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

@Composable
private fun HandlerUiState(uiState: State<BaseUiState>) {
    val snackBar = LocalSnackBar.current

    LaunchedEffect(uiState.value) {
        when (uiState.value) {
            is BaseUiState.Error -> {
                (uiState.value as BaseUiState.Error).message?.let { snackBar.showSnackbar(it) }
            }
            else -> Unit
        }
    }
}