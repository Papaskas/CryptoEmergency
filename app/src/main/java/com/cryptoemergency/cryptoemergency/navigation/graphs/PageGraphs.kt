package com.cryptoemergency.cryptoemergency.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.cryptoemergency.cryptoemergency.navigation.Routes
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
    composable<Routes.Page.News> { NewsScreen() }
    composable<Routes.Page.Web3> { Web3Screen() }
    composable<Routes.Page.Career> { CareerScreen() }
    composable<Routes.Page.Wallet> { WalletScreen() }
    composable<Routes.Page.Users> { UsersScreen() }
    composable<Routes.Page.Chat> { ChatScreen() }
    composable<Routes.Page.Academy> { AcademyScreen() }
    composable<Routes.Page.Exchangers> { ExchangersScreen() }
    composable<Routes.Page.Exchanges> { ExchangesScreen() }
    composable<Routes.Page.ICORating> { ICORatingScreen() }
    composable<Routes.Page.NewsFeed> { NewsFeedScreen() }
    composable<Routes.Page.Startups> { StartupsScreen() }

    composable<Routes.Page.QRCode> {
        val params: Routes.Page.QRCode = it.toRoute()

        QRCodeScreen(params.text)
    }
}
