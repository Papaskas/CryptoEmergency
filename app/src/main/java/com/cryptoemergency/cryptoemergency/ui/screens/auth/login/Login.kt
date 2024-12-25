package com.cryptoemergency.cryptoemergency.ui.screens.auth.login

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.ui.common.SteppedScreen
import com.cryptoemergency.cryptoemergency.ui.screens.auth.login.steps.FirstStep
import com.cryptoemergency.cryptoemergency.ui.screens.auth.login.steps.SecondStep

@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {
    val screens = listOf<@Composable () -> Unit>(
        { FirstStep(viewModel) },
        { SecondStep(viewModel) }
    )

    SteppedScreen(viewModel.currentStep, screens)
}
