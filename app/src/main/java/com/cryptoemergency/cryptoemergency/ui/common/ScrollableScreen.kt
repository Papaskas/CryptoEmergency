package com.cryptoemergency.cryptoemergency.ui.common

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.providers.theme.Theme

@Composable
fun ScrollableScreen(
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(horizontal = Theme.values.padding),
    bottomSpacing: Dp = 35.dp,
    state: ScrollState = rememberScrollState(),
    content: @Composable ColumnScope.() -> Unit,
) {
    Screen(
        bottomSpacing = bottomSpacing,
        padding = padding,
        modifier = modifier
            .verticalScroll(state)
            .imePadding()
    ) {
        content()
    }
}
