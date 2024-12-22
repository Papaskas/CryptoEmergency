package com.cryptoemergency.cryptoemergency.ui.screens.pages.qrCode

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.modifiers.copyTextToClipboard
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.CommonHorizontalDivider
import com.cryptoemergency.cryptoemergency.ui.common.Screen
import com.cryptoemergency.cryptoemergency.ui.common.buttons.ButtonType
import com.cryptoemergency.cryptoemergency.ui.common.buttons.CommonButton
import com.cryptoemergency.cryptoemergency.ui.common.topBar.ScreenTopBar
import com.lightspark.composeqr.QrCodeColors
import com.lightspark.composeqr.QrCodeView

@Composable
fun QRCodeScreen(
    text: String,
    viewModel: QRCodeViewModel = hiltViewModel()
) {
    Screen(
        topBar = { ScreenTopBar(title = "") },
        bottomBar = {},
        horizontalPadding = 0.dp,
        bottomSpacing = 0.dp,
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
        ) {
            Spacer(Modifier.weight(1f))

            QRCode(
                text = text,
                color = viewModel.selectedOption.value,
            )

            Spacer(Modifier.weight(1f))

            BottomNav(viewModel, text)
        }
    }
}

@Composable
private fun QRCode(
    text: String,
    color: Color,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(horizontal = 68.dp,),
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .border(
                    1.dp,
                    Theme.colors.strokeVariant,
                    RoundedCornerShape(20.dp)
                )
                .padding(
                    top = 30.dp,
                    bottom = 20.dp,
                )
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            QrCodeView(
                data = text,
                colors = QrCodeColors(
                    background = Color.White,
                    foreground = color,
                ),
                modifier = Modifier.size(170.dp),
            ) { AboveIcon(text, color) }

            Title(text, color)
        }
    }
}

@Composable
private fun Title(
    text: String,
    color: Color,
) {
    Spacer(Modifier.height(Theme.dimens.padding))

    Text(
        text = text,
        textAlign = TextAlign.Center,
        color = color,
        style = Theme.typography.h2,
    )
}

@Composable
private fun AboveIcon(
    text: String,
    color: Color,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(55.dp)
                .background(Color.White)
                .clip(CircleShape)
                .background(color)
        ) {
            Icon(
                painter = painterResource(R.drawable.cem_coin),
                contentDescription = text,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
private fun BottomNav(
    viewModel: QRCodeViewModel,
    text: String,
) {
    Surface(
        shape = RoundedCornerShape(
            topStart = Theme.dimens.radius,
            topEnd = Theme.dimens.radius,
        ),
        color = Theme.colors.surface3,
        modifier = Modifier
            .border(
                1.dp,
                Theme.colors.stroke,
                RoundedCornerShape(
                    topStart = Theme.dimens.radius,
                    topEnd = Theme.dimens.radius,
                )
            )
    ) {
        Column {
            Heading()
            Body(viewModel, text)
        }
    }
}

@Composable
private fun Heading() {
    Column {
        Text(
            text = "QR-code",
            style = Theme.typography.h2,
            color = Theme.colors.text1,
            modifier = Modifier.padding(Theme.dimens.padding)
        )

        CommonHorizontalDivider()
    }
}

@Composable
private fun Body(
    viewModel: QRCodeViewModel,
    text: String,
) {
    val context = LocalContext.current

    Column(
        Modifier.padding(Theme.dimens.padding)
    ) {
        Row {
            viewModel.options.forEach {
                SelectableItem(
                    tint = it,
                    viewModel = viewModel,
                )
                Spacer(Modifier.width(Theme.dimens.padding))
            }
        }

        Spacer(Modifier.height(Theme.dimens.padding))

        CommonButton(
            onClick = { /*TODO*/ },
            text = "Поделиться",
        )

        Spacer(Modifier.height(10.dp))

        CommonButton(
            onClick = { copyTextToClipboard(context, text, text) },
            text = "Копировать ссылку",
            buttonType = ButtonType.Secondary,
        )
    }
}

@Composable
private fun SelectableItem(
    tint: Color,
    viewModel: QRCodeViewModel,
) {
    Surface(
        shape = RoundedCornerShape(Theme.dimens.radius),
        color = Theme.colors.surface2,
        modifier = Modifier
            .then(
                if (viewModel.selectedOption.value == tint) {
                    Modifier.border(
                        1.dp,
                        Theme.colors.accent,
                        RoundedCornerShape(Theme.dimens.radius)
                    )
                } else {
                    Modifier
                }
            )
            .clickable(
                interactionSource = null,
                indication = ripple(),
            ) {
                viewModel.selectedOption.value = tint
            }
    ) {
        Surface(
            modifier = Modifier

                .padding(20.dp),
            color = Color.White,
            shape = RoundedCornerShape(Theme.dimens.radius)
        ) {
            Icon(
                modifier = Modifier
                    .background(Color.White)
                    .border(
                        1.dp,
                        Theme.colors.strokeVariant,
                        RoundedCornerShape(Theme.dimens.radius)
                    )
                    .padding(10.dp),
                painter = painterResource(R.drawable.qr_filled),
                contentDescription = null,
                tint = tint,
            )
        }
    }
}
