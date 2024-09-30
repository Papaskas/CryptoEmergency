package com.cryptoemergency.cryptoemergency.ui.screens.pages.login.steps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.navigation.Destination
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.CommonHorizontalDivider
import com.cryptoemergency.cryptoemergency.ui.common.Screen
import com.cryptoemergency.cryptoemergency.ui.common.buttons.ButtonType
import com.cryptoemergency.cryptoemergency.ui.common.buttons.CommonButton
import com.cryptoemergency.cryptoemergency.ui.screens.pages.login.LoginViewModel

@Composable
fun FirstStep(viewModel: LoginViewModel) {
    Screen(
        topBar = {},
        bottomBar = {},
        horizontalPadding = 0.dp
    ) {
        Spacer(Modifier.weight(1f))
        Header()
        Spacer(Modifier.weight(1f))
        Buttons(viewModel)
        Terms()
    }
}

@Composable
private fun Header() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(Theme.icons.logo),
            contentDescription = null,
            modifier = Modifier
                .size(
                    width = 250.dp,
                    height = 100.dp
                )
        )
        Spacer(Modifier.height(20.dp))
        CommonHorizontalDivider()
        Text(
            text = "Твой мир в криптоиндустрии",
            color = Theme.colors.text1,
            style = Theme.typography.body1,
            modifier = Modifier.padding(vertical = 19.dp),
            textAlign = TextAlign.Center,
        )
        CommonHorizontalDivider()
    }
}

@Composable
private fun Buttons(viewModel: LoginViewModel) {
    Column(
        Modifier.padding(horizontal = Theme.dimens.padding)
    ) {
        CommonButton("Войти") {
            viewModel.currentStep.intValue = 2
        }
        Spacer(Modifier.height(10.dp))
        CommonButton("Продолжить как гость", buttonType = ButtonType.Secondary) {
            viewModel.continueAsGuest()
        }
    }
}

@Composable
private fun Terms() {
    val navController = LocalNavController.current

    val annotatedString = buildAnnotatedString {
        append("Нажимая на кнопку «Войти» или «Продолжить как гость», вы соглашаетесь с ")
        pushStringAnnotation(tag = "TERMS", annotation = "")
        withStyle(style = SpanStyle(color = Theme.colors.accent)) {
            append("обработкой персональных данных и политикой конфиденциальности")
        }
        pop()
    }

    ClickableText(
        modifier = Modifier.padding(
            horizontal = Theme.dimens.padding,
            vertical = Theme.dimens.padding * 2,
        ),
        text = annotatedString,
        style = Theme.typography.caption1.copy(
            color = Theme.colors.text2
        ),
    ) {
        annotatedString.getStringAnnotations(tag = "TERMS", start = it, end = it)
            .firstOrNull()?.let {
                navController.navigate(Destination.Home.Home)
            }
    }
}
