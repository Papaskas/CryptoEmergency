package com.cryptoemergency.cryptoemergency.ui.screens.post.createPost

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.ui.common.SteppedScreen
import com.cryptoemergency.cryptoemergency.ui.screens.post.CreatePostViewModel
import com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.steps.FirstStep
import com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.steps.SecondStep

@Composable
fun CreatePostScreen(
    viewModel: CreatePostViewModel = hiltViewModel()
) {
    val screens = listOf<@Composable () -> Unit>(
        { FirstStep(viewModel) },
        { SecondStep(viewModel) },
    )

    SteppedScreen(
        screens = screens,
        currentStep = viewModel.currentStep,
    )
}
