package com.cryptoemergency.cryptoemergency.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.cryptoemergency.cryptoemergency.navigation.graphs.authGraphs
import com.cryptoemergency.cryptoemergency.navigation.graphs.homeGraphs
import com.cryptoemergency.cryptoemergency.navigation.graphs.pageGraphs
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController

@Composable
fun Navigation() {
    val navController = LocalNavController.current

    NavHost(
        navController = navController,
        startDestination = Routes.Home.Home,
        enterTransition = { fadeIn(animationSpec = tween(200)) },
    ) {
        homeGraphs()
        authGraphs()
        pageGraphs()
    }
}
