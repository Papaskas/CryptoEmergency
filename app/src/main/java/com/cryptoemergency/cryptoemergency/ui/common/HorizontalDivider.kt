package com.cryptoemergency.cryptoemergency.ui.common

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.providers.theme.Theme

@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier
) {
    HorizontalDivider(modifier = modifier, thickness = 1.dp, color = Theme.colors.stroke)
}
