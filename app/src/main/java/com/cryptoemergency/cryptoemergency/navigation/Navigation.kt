package com.cryptoemergency.cryptoemergency.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import com.cryptoemergency.cryptoemergency.api.store.Store
import com.cryptoemergency.cryptoemergency.navigation.graphs.authGraphs
import com.cryptoemergency.cryptoemergency.navigation.graphs.homeGraphs
import com.cryptoemergency.cryptoemergency.navigation.graphs.pageGraphs
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController
import com.cryptoemergency.cryptoemergency.repository.store.Keys

@Composable
fun Navigation() {
    val navController = LocalNavController.current
    val context = LocalContext.current

    val token by produceState<String?>(null) {
        value = Store(Keys.TOKEN, context).get()
    }

    if (token != null) {
        NavHost(
            navController = navController,
            startDestination =
            if (token != Keys.TOKEN.defaultValue) Destination.Home.Home
            else Destination.Auth.Login,
            enterTransition = { fadeIn(animationSpec = tween(200)) },
        ) {
            homeGraphs()
            authGraphs()
            pageGraphs()
        }
    }
}
