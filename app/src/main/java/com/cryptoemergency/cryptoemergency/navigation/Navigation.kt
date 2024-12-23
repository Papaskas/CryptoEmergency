package com.cryptoemergency.cryptoemergency.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import com.cryptoemergency.cryptoemergency.navigation.graphs.authGraphs
import com.cryptoemergency.cryptoemergency.navigation.graphs.homeGraphs
import com.cryptoemergency.cryptoemergency.navigation.graphs.pageGraphs
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController

@Composable
fun Navigation(
    vm: NavigationViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val token = vm.token.collectAsState().value

    if (token != null) {
        NavHost(
            navController = navController,
            startDestination =
                if (token.isNotEmpty()) Destination.Home.Home
                else Destination.Auth.Login,
            enterTransition = { fadeIn(animationSpec = tween(200)) },
        ) {
            homeGraphs()
            authGraphs()
            pageGraphs()
        }
    }
}
