package com.cryptoemergency.cryptoemergency.ui.common

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ScrollableScreen(
    modifier: Modifier = Modifier,
    state: ScrollState = rememberScrollState(),
    content: @Composable ColumnScope.() -> Unit,
) {
    Screen(
        modifier
            .verticalScroll(state)
            .imePadding()
    ) {
        content()
    }
}

