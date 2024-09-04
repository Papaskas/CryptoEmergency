package com.cryptoemergency.cryptoemergency.ui.screens.pages.qrCode

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.modifiers.commonBorder
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.CommonButton
import com.cryptoemergency.cryptoemergency.ui.common.CommonHorizontalDivider
import com.cryptoemergency.cryptoemergency.ui.common.Screen
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
        padding = PaddingValues(0.dp),
        bottomSpacing = 0.dp,
    ) {
        QRCode(
            text = text,
            color = viewModel.selectedOption.value,
        )

        BottomNav(viewModel)
    }
}

@Composable
private fun ColumnScope.QRCode(
    text: String,
    color: Color,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .weight(1f),
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(Theme.values.shape))
                .background(Color.White)
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
                modifier = Modifier
                    .size(300.dp),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(15.dp)
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

            Spacer(Modifier.height(Theme.values.padding))

            Text(
                text = text,
                textAlign = TextAlign.Center,
                color = color,
                style = Theme.typography.h2,
            )
        }
    }
}

@Composable
private fun BottomNav(
    viewModel: QRCodeViewModel
) {
    Surface(
        shape = RoundedCornerShape(
            topStart = Theme.values.shape,
            topEnd = Theme.values.shape,
        ),
        color = Theme.colors.surface1,
        modifier = Modifier
            .commonBorder(
                shape = RoundedCornerShape(
                    topStart = Theme.values.shape,
                    topEnd = Theme.values.shape,
                )
            )
    ) {
        Column {
            Heading()
            Body(viewModel)
        }
    }
}

@Composable
private fun Heading() {
    Column {
        Text(
            text = "QR-код",
            style = Theme.typography.h2,
            color = Theme.colors.text1,
            modifier = Modifier.padding(Theme.values.padding)
        )

        CommonHorizontalDivider()
    }
}

@Composable
private fun Body(
    viewModel: QRCodeViewModel,
) {
    Column(
        Modifier.padding(Theme.values.padding)
    ) {
        Row {
            viewModel.options.forEach {
                SelectableItem(
                    tint = it,
                    viewModel = viewModel,
                )
                Spacer(Modifier.width(Theme.values.padding))
            }
        }

        Spacer(Modifier.height(Theme.values.padding))

        CommonButton(onClick = { /*TODO*/ }, text = "Поделиться")
    }
}

@Composable
private fun SelectableItem(
    tint: Color,
    viewModel: QRCodeViewModel,
) {
    Surface(
        color = Theme.colors.surface2,
        modifier = Modifier
            .then(
                if (viewModel.selectedOption.value == tint) {
                    Modifier.border(1.dp, Theme.colors.accent, RoundedCornerShape(Theme.values.shape))
                } else {
                    Modifier
                }
            )
            .clip(RoundedCornerShape(Theme.values.shape))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
            ) {
                viewModel.selectedOption.value = tint
            }
    ) {
        Surface(
            modifier = Modifier.padding(20.dp),
            color = Color.White,
            shape = RoundedCornerShape(Theme.values.shape)
        ) {
            Icon(
                modifier = Modifier
                    .border(
                        1.dp,
                        Theme.colors.strokeVariant,
                        RoundedCornerShape(Theme.values.shape)
                    )
                    .background(Color.White)
                    .padding(10.dp),
                painter = painterResource(R.drawable.qr_filled),
                contentDescription = null,
                tint = tint,
            )
        }
    }
}
