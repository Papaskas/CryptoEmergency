package com.cryptoemergency.cryptoemergency.ui.common.topBar

import androidx.compose.runtime.Composable
import com.cryptoemergency.cryptoemergency.providers.localNavController.getCurrentRoute
import com.cryptoemergency.cryptoemergency.ui.common.topBar.components.MainTopBar

@Composable
fun TopBar() {
    val currentRoute = getCurrentRoute()

    when (currentRoute) {
        else -> MainTopBar()
    }
}

@Composable
private fun EmptyTopBar() {
    // detekt-disable-next-line EmptyFunctionBlock
}
