package com.cryptoemergency.cryptoemergency.navigation

import kotlinx.serialization.Serializable

/**
 * Закрытый класс, представляющий различные маршруты в приложении. Этот класс гарантирует,
 * что в нем определены все возможные маршруты, обеспечивая безопасность типов.
 * Внутри определяются интрефейсы для блокировки редиректа на них.
 **/
sealed class Routes {
    interface Home {
        @Serializable
        data object Home : Routes()

        @Serializable
        data object News : Routes()

        @Serializable
        data object CreatePost : Routes()

        @Serializable
        data object Cems : Routes()

        @Serializable
        data object Menu : Routes()
    }

    interface Page {
        @Serializable
        data object NewsFeed : Routes()

        @Serializable
        data object Chat : Routes()

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

        @Serializable
        data class QRCode(
            val text: String
        ) : Routes()
    }

    interface Auth {
        @Serializable
        data object Profile : Routes()

        @Serializable
        data object ChangeProfileData : Routes()
    }
}
