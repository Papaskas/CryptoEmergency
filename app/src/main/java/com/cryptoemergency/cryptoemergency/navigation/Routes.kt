package com.cryptoemergency.cryptoemergency.navigation

import android.net.Uri
import androidx.annotation.Keep
import kotlinx.serialization.Contextual
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
sealed class Routes {

    @Keep
    interface Home {
        @Keep
        @Serializable
        data object Home : Routes()

        @Keep
        @Serializable
        data object News : Routes()

        @Keep
        @Serializable
        data object Cems : Routes()

        @Keep
        @Serializable
        data object Menu : Routes()
    }

    @Keep
    interface CreatePost {
        @Keep
        @Serializable
        data object Home : Routes()

        @Keep
        @Serializable
        data class ModifyPost(
            val media: List<String>
        ) : Routes()

        @Keep
        @Serializable
        data object GetGeolocation: Routes()
    }

    @Keep
    interface Page {
        @Keep
        @Serializable
        data object NewsFeed : Routes()

        @Keep
        @Serializable
        data object Chat : Routes()

        @Keep
        @Serializable
        data object News : Routes()

        @Keep
        @Serializable
        data object Exchangers : Routes()

        @Keep
        @Serializable
        data object Exchanges : Routes()

        @Keep
        @Serializable
        data object Users : Routes()

        @Keep
        @Serializable
        data object ICORating : Routes()

        @Keep
        @Serializable
        data object Startups : Routes()

        @Keep
        @Serializable
        data object Web3 : Routes()

        @Keep
        @Serializable
        data object Career : Routes()

        @Keep
        @Serializable
        data object Academy : Routes()

        @Keep
        @Serializable
        data object Wallet : Routes()

        @Keep
        @Serializable
        data class QRCode(
            val text: String
        ) : Routes()
    }

    @Keep
    interface Auth {
        @Keep
        @Serializable
        data object Profile : Routes()

        @Keep
        @Serializable
        data object ChangeProfileData : Routes()
    }
}
