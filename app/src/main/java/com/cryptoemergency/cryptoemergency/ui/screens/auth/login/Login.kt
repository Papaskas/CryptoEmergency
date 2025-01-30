package com.cryptoemergency.cryptoemergency.ui.screens.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.navigation.Destination
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController
import com.cryptoemergency.cryptoemergency.providers.localSnackBar.LocalSnackBar
import com.cryptoemergency.cryptoemergency.ui.common.SteppedScreen
import com.cryptoemergency.cryptoemergency.ui.screens.auth.login.steps.FirstStep
import com.cryptoemergency.cryptoemergency.ui.screens.auth.login.steps.SecondStep

@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {
    val screens = listOf<@Composable () -> Unit>(
        { FirstStep(viewModel) },
        { SecondStep(viewModel) }
    )

    HandlerUiState(viewModel.uiState.collectAsState())

    SteppedScreen(viewModel.currentStep, screens)
}

@Composable
private fun HandlerUiState(uiState: State<UiState>) {
    val navController = LocalNavController.current
    val snackBar = LocalSnackBar.current

    LaunchedEffect(uiState.value) {
        when (uiState.value) {
            is UiState.LoginSuccess -> {
                snackBar.showSnackbar("Успешная авторизация")
                navController.navigate(Destination.Home.Home)
            }
            is UiState.LoginError -> {
                (uiState.value as UiState.LoginError).message?.let { snackBar.showSnackbar(it) }
            }
            is UiState.ContinueAsGuestSuccess -> {
                snackBar.showSnackbar("Успешно")
                navController.navigate(Destination.Home.Home)
            }
            is UiState.ContinueAsGuestError -> {
                (uiState.value as UiState.ContinueAsGuestError).message?.let { snackBar.showSnackbar(it) }
            }
            else -> Unit
        }
    }
}