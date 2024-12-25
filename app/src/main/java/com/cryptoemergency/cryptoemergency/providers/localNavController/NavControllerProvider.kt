package com.cryptoemergency.cryptoemergency.providers.localNavController

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController

@Composable
fun NavControllerProvider(content: @Composable () -> Unit) {
    val navController = rememberNavController()

    CompositionLocalProvider(LocalNavController provides navController) {
        content()
    }
}