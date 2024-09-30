package com.cryptoemergency.cryptoemergency.navigation

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

/**
 * Закрытый класс, представляющий различные маршруты в приложении. Этот класс гарантирует,
 * что в нем определены все возможные маршруты, обеспечивая безопасность типов.
 * Внутри определяются интрефейсы для блокировки редиректа на них.
 *
 * @Keep В release сборке имена классов минифицируется, Keep игнорит это
 **/
@Keep
@Serializable
sealed class Destination {

    @Keep
    interface Home {
        @Keep
        @Serializable
        data object Home : Destination()

        @Keep
        @Serializable
        data object News : Destination()

        @Keep
        @Serializable
        data object CreatePost : Destination()

        @Keep
        @Serializable
        data object Cems : Destination()

        @Keep
        @Serializable
        data object Menu : Destination()
    }

    @Keep
    interface Page {
        @Keep
        @Serializable
        data object NewsFeed : Destination()

        @Keep
        @Serializable
        data object Chat : Destination()

        @Keep
        @Serializable
        data object News : Destination()

        @Keep
        @Serializable
        data object Exchangers : Destination()

        @Keep
        @Serializable
        data object Exchanges : Destination()

        @Keep
        @Serializable
        data object Users : Destination()

        @Keep
        @Serializable
        data object ICORating : Destination()

        @Keep
        @Serializable
        data object Startups : Destination()

        @Keep
        @Serializable
        data object Web3 : Destination()

        @Keep
        @Serializable
        data object Career : Destination()

        @Keep
        @Serializable
        data object Academy : Destination()

        @Keep
        @Serializable
        data object Wallet : Destination()

        @Keep
        @Serializable
        data class QRCode(
            val text: String
        ) : Destination()
    }

    @Keep
    interface Auth {
        @Keep
        @Serializable
        data object Profile : Destination()

        @Keep
        @Serializable
        data object ChangeProfileData : Destination()

        @Keep
        @Serializable
        data object Login : Destination()
    }
}
