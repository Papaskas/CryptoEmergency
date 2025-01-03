package com.cryptoemergency.cryptoemergency.ui.common

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.providers.theme.Theme

@Composable
fun CommonHorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 1.dp,
    color: Color = Theme.colors.stroke,
) {
    HorizontalDivider(modifier = modifier, thickness = thickness, color = color)
}
