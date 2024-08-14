package com.cryptoemergency.cryptoemergency.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.providers.theme.Theme

@Composable
fun Screen(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier.padding(horizontal = Theme.shapes.padding),
    ) {
        content()
        Spacer(Modifier.height(35.dp))
    }
}