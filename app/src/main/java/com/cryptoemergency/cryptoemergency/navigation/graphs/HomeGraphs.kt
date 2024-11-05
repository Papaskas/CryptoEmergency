package com.cryptoemergency.cryptoemergency.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cryptoemergency.cryptoemergency.navigation.Destination
import com.cryptoemergency.cryptoemergency.ui.screens.home.HomeScreen

fun NavGraphBuilder.homeGraphs() {
    composable<Destination.Home.Home> { HomeScreen() }
}
