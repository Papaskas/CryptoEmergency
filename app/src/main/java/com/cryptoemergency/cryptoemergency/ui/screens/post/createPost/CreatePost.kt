package com.cryptoemergency.cryptoemergency.ui.screens.post.createPost

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.common.BaseUiState
import com.cryptoemergency.cryptoemergency.providers.localSnackBar.LocalSnackBar
import com.cryptoemergency.cryptoemergency.ui.common.SteppedScreen
import com.cryptoemergency.cryptoemergency.ui.screens.auth.login.UiState
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

    HandlerUiState(viewModel.uiState.collectAsState())

    SteppedScreen(
        screens = screens,
        steps = viewModel.currentStep,
    )
}

@Composable
private fun HandlerUiState(uiState: State<BaseUiState>) {
    val snackBar = LocalSnackBar.current

    LaunchedEffect(uiState.value) {
        when (uiState.value) {
            is BaseUiState.Success -> {
                (uiState.value as BaseUiState.Success).message?.let { snackBar.showSnackbar(it) }
            }
            is BaseUiState.Error -> {
                (uiState.value as BaseUiState.Error).message?.let { snackBar.showSnackbar(it) }
            }
            else -> Unit
        }
    }
}