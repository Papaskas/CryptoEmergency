package com.cryptoemergency.cryptoemergency.ui.common.topBar

import androidx.compose.runtime.Composable
import com.cryptoemergency.cryptoemergency.navigation.Routes
import com.cryptoemergency.cryptoemergency.providers.localNavController.getCurrentRoute
import com.cryptoemergency.cryptoemergency.ui.common.topBar.components.MainTopBar
import com.cryptoemergency.cryptoemergency.ui.common.topBar.components.ProfileTopBar
import com.cryptoemergency.cryptoemergency.ui.common.topBar.components.ScreenTopBar

@Composable
fun TopBar() {
    val currentRoute = getCurrentRoute()

    when (currentRoute) {
        Routes.Auth.Profile::class.qualifiedName -> ProfileTopBar()
        Routes.Auth.ChangeProfileData::class.qualifiedName -> ScreenTopBar("Редактировать профиль")

        else -> MainTopBar()
    }
}

@Composable
private fun EmptyTopBar() {
    // detekt-disable-next-line EmptyFunctionBlock
}
