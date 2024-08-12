package com.cryptoemergency.cryptoemergency.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cryptoemergency.cryptoemergency.navigation.Routes
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.ProfileScreen

fun NavGraphBuilder.authGraphs() {
    composable<Routes.Auth.Profile> { ProfileScreen() }
}
