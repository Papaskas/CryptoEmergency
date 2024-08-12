package com.cryptoemergency.cryptoemergency.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    object Home {
        @Serializable
        data object Home : Routes()

        @Serializable
        data object News : Routes()

        @Serializable
        data object Chat : Routes()

        @Serializable
        data object Menu : Routes()
    }

    object Page {
        @Serializable
        data object NewsFeed : Routes()

        @Serializable
        data object News : Routes()

        @Serializable
        data object Exchangers : Routes()

        @Serializable
        data object Exchanges : Routes()

        @Serializable
        data object Users : Routes()

        @Serializable
        data object ICORating : Routes()

        @Serializable
        data object Startups : Routes()

        @Serializable
        data object Web3 : Routes()

        @Serializable
        data object Career : Routes()

        @Serializable
        data object Academy : Routes()

        @Serializable
        data object Wallet : Routes()
    }

    object Auth {
        @Serializable
        data object Profile : Routes()
    }
}
