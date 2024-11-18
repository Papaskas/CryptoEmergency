package com.cryptoemergency.cryptoemergency.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.cryptoemergency.cryptoemergency.navigation.Destination
import com.cryptoemergency.cryptoemergency.ui.screens.pages.academy.AcademyScreen
import com.cryptoemergency.cryptoemergency.ui.screens.pages.career.CareerScreen
import com.cryptoemergency.cryptoemergency.ui.screens.pages.chat.ChatScreen
import com.cryptoemergency.cryptoemergency.ui.screens.pages.exchangers.ExchangersScreen
import com.cryptoemergency.cryptoemergency.ui.screens.pages.exchanges.ExchangesScreen
import com.cryptoemergency.cryptoemergency.ui.screens.pages.icoRating.ICORatingScreen
import com.cryptoemergency.cryptoemergency.ui.screens.pages.news.NewsScreen
import com.cryptoemergency.cryptoemergency.ui.screens.pages.newsFeed.NewsFeedScreen
import com.cryptoemergency.cryptoemergency.ui.screens.pages.qrCode.QRCodeScreen
import com.cryptoemergency.cryptoemergency.ui.screens.pages.startups.StartupsScreen
import com.cryptoemergency.cryptoemergency.ui.screens.pages.users.UsersScreen
import com.cryptoemergency.cryptoemergency.ui.screens.pages.wallet.WalletScreen
import com.cryptoemergency.cryptoemergency.ui.screens.pages.web3.Web3Screen

fun NavGraphBuilder.pageGraphs() {
    composable<Destination.Page.News> { NewsScreen() }
    composable<Destination.Page.Web3> { Web3Screen() }
    composable<Destination.Page.Career> { CareerScreen() }
    composable<Destination.Page.Wallet> { WalletScreen() }
    composable<Destination.Page.Users> { UsersScreen() }
    composable<Destination.Page.Chat> { ChatScreen() }
    composable<Destination.Page.Academy> { AcademyScreen() }
    composable<Destination.Page.Exchangers> { ExchangersScreen() }
    composable<Destination.Page.Exchanges> { ExchangesScreen() }
    composable<Destination.Page.ICORating> { ICORatingScreen() }
    composable<Destination.Page.NewsFeed> { NewsFeedScreen() }
    composable<Destination.Page.Startups> { StartupsScreen() }

    composable<Destination.Page.QRCode> {
        val params: Destination.Page.QRCode = it.toRoute()

        QRCodeScreen(params.text)
    }
}
