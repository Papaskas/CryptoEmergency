package com.cryptoemergency.cryptoemergency.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cryptoemergency.cryptoemergency.navigation.Routes
import com.cryptoemergency.cryptoemergency.ui.screens.home.chat.ChatScreen
import com.cryptoemergency.cryptoemergency.ui.screens.home.home.HomeScreen
import com.cryptoemergency.cryptoemergency.ui.screens.home.menu.MenuScreen
import com.cryptoemergency.cryptoemergency.ui.screens.home.news.NewsScreen

fun NavGraphBuilder.homeGraphs() {
    composable<Routes.Home.Home> { HomeScreen() }
    composable<Routes.Home.Chat> { ChatScreen() }
    composable<Routes.Home.Menu> { MenuScreen() }
    composable<Routes.Home.News> { NewsScreen() }
    composable<Routes.Home.AddedStory> { Temp() }
}

@Composable
fun Temp() {

}
