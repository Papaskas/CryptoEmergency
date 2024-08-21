package com.cryptoemergency.cryptoemergency.ui.common

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cryptoemergency.cryptoemergency.providers.theme.Theme

@Composable
fun ScrollableScreen(
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(horizontal = Theme.values.padding),
    state: ScrollState = rememberScrollState(),
    content: @Composable ColumnScope.() -> Unit,
) {
    Screen(
        padding = padding,
        modifier = modifier
            .verticalScroll(state)
            .imePadding()
    ) {
        content()
    }
}
