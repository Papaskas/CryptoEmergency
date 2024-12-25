package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.providers.theme.provides.Theme
import com.cryptoemergency.cryptoemergency.ui.common.buttons.CommonButton
import com.cryptoemergency.cryptoemergency.ui.common.buttons.LayoutWidth

/**
 * Заглушка для блоков Content
 * */
@Composable
fun EmptyProfilePage(
    title: String,
    onClick: () -> Unit,
    message: String,
    buttonText: String,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 30.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(Modifier.weight(1f))

            Text(
                text = title,
                style = Theme.typography.h3,
                color = Theme.colors.text1,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = message,
                style = Theme.typography.caption1,
                color = Theme.colors.text2,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(20.dp))
            CommonButton(
                text = buttonText,
                onClick = onClick,
                layoutWidth = LayoutWidth.WrapContent,
            )

            Spacer(Modifier.weight(1f))
        }
    }
}
