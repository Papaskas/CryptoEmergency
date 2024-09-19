package com.cryptoemergency.cryptoemergency.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cryptoemergency.cryptoemergency.navigation.Routes
import com.cryptoemergency.cryptoemergency.ui.screens.createPost.CreatePostViewModel
import com.cryptoemergency.cryptoemergency.ui.screens.createPost.home.CreatePostScreen
import com.cryptoemergency.cryptoemergency.ui.screens.createPost.modifyPost.ModifyPostScreen

fun NavGraphBuilder.createPostGraph(
    viewModel: CreatePostViewModel
) {
    composable<Routes.CreatePost.Home> { CreatePostScreen(viewModel) }
    composable<Routes.CreatePost.ModifyPost> { ModifyPostScreen(viewModel) }
}
