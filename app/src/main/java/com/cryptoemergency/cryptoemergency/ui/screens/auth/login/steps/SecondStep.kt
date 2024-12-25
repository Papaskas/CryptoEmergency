package com.cryptoemergency.cryptoemergency.ui.screens.auth.login.steps

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.providers.theme.provides.Theme
import com.cryptoemergency.cryptoemergency.ui.common.Screen
import com.cryptoemergency.cryptoemergency.ui.common.buttons.CommonButton
import com.cryptoemergency.cryptoemergency.ui.common.inputs.EmailInput
import com.cryptoemergency.cryptoemergency.ui.common.inputs.PasswordInput
import com.cryptoemergency.cryptoemergency.ui.screens.auth.login.LoginViewModel

@Composable
fun SecondStep(viewModel: LoginViewModel) {
    //Listener(viewModel.message, viewModel.redirect.collectAsState().value)

    Screen(
        topBar = {},
        bottomBar = { BottomBar(viewModel) },
    ) {
        Column(Modifier.padding(it)) {
            HelloText()
            Inputs(viewModel)
            ForgotPassword(viewModel)
        }
    }
}

@Composable
private fun HelloText() {
    Spacer(Modifier.height(15.dp))

    Text(
        text = "Приветсвуем Вас, с возвращением!",
        style = Theme.typography.helloText,
        color = Theme.colors.text1,
    )

    Spacer(Modifier.height(40.dp))
}

@Composable
private fun Inputs(viewModel: LoginViewModel) {
    EmailInput(
        viewModel.emailInput,
        viewModel.emailError,
    )
    Spacer(Modifier.height(10.dp))
    PasswordInput(
        viewModel.passwordInput,
        viewModel.passwordError,
    )
}

@Composable
private fun ColumnScope.ForgotPassword(viewModel: LoginViewModel) {
    Spacer(Modifier.height(10.dp))

    Text(
        text = "Забыли пароль?",
        color = Theme.colors.accent,
        style = Theme.typography.body1,
        modifier = Modifier
            .clickable(
                interactionSource = null,
                indication = null,
            ) {

            }
            .align(Alignment.End),
    )
}

@Composable
private fun BottomBar(viewModel: LoginViewModel) {
    Box(Modifier
        .padding(horizontal = Theme.dimens.horizontalPadding)
        .padding(bottom = Theme.dimens.horizontalPadding * 2)
        .imePadding()
    ) {
        CommonButton(
        isEnabled =
            !viewModel.passwordError.value &&
            !viewModel.emailError.value &&
            viewModel.emailInput.value.text.isNotEmpty() &&
            viewModel.passwordInput.value.text.isNotEmpty(),
            text = "Войти",
        ) {
            viewModel.login()
        }
    }
}
