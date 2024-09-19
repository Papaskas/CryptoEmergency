package com.cryptoemergency.cryptoemergency.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cryptoemergency.cryptoemergency.navigation.Routes
import com.cryptoemergency.cryptoemergency.ui.screens.home.cems.CemsScreen
import com.cryptoemergency.cryptoemergency.ui.screens.home.home.HomeScreen
import com.cryptoemergency.cryptoemergency.ui.screens.home.menu.MenuScreen
import com.cryptoemergency.cryptoemergency.ui.screens.home.newsFeed.NewsFeedScreen

fun NavGraphBuilder.homeGraphs() {
    composable<Routes.Home.Home> { HomeScreen() }
    composable<Routes.Home.News> { NewsFeedScreen() }
    composable<Routes.Home.Cems> { CemsScreen() }
    composable<Routes.Home.Menu> { MenuScreen() }
}
