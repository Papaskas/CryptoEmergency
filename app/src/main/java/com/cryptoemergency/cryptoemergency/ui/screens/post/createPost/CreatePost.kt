package com.cryptoemergency.cryptoemergency.ui.screens.post.createPost

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.ui.common.SteppedScreen
import com.cryptoemergency.cryptoemergency.ui.screens.post.CreatePostViewModel
import com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.steps.FirstStep
import com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.steps.SecondStep

@Composable
fun CreatePostScreen(
    viewModel: CreatePostViewModel = hiltViewModel()
) {
    val currentStep = remember { mutableIntStateOf(0) }

    val screens = listOf<@Composable () -> Unit>(
        { FirstStep(currentStep, viewModel) },
        { SecondStep(currentStep, viewModel) },
    )

    SteppedScreen(
        screens = screens,
        currentStep = currentStep,
    )
}
