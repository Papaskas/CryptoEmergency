package com.cryptoemergency.cryptoemergency.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cryptoemergency.cryptoemergency.navigation.Destination
import com.cryptoemergency.cryptoemergency.ui.screens.home.cems.CemsScreen
import com.cryptoemergency.cryptoemergency.ui.screens.home.home.HomeScreen
import com.cryptoemergency.cryptoemergency.ui.screens.home.menu.MenuScreen
import com.cryptoemergency.cryptoemergency.ui.screens.home.newsFeed.NewsFeedScreen
import com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.CreatePostScreen

fun NavGraphBuilder.homeGraphs() {
    composable<Destination.Home.Home> { HomeScreen() }
    composable<Destination.Home.News> { NewsFeedScreen() }
    composable<Destination.Home.CreatePost> { CreatePostScreen() }
    composable<Destination.Home.Cems> { CemsScreen() }
    composable<Destination.Home.Menu> { MenuScreen() }
}
