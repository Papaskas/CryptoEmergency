package com.cryptoemergency.cryptoemergency.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController
import com.cryptoemergency.cryptoemergency.ui.screens.home.home.HomeScreen

@Composable
fun Navigation() {
    val navController = LocalNavController.current

    NavHost(navController = navController, startDestination = Routes.Home.Home) {
        composable<Routes.Home.Home> {
            HomeScreen()
        }

        composable<Routes.Auth.Profile> {
        }
    }
}
