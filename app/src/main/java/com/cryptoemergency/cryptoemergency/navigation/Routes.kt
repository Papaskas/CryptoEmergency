package com.cryptoemergency.cryptoemergency.navigation

import kotlinx.serialization.Serializable

object Routes {
    object Home {
        @Serializable
        object Home

        @Serializable
        object News

        @Serializable
        object Chat

        @Serializable
        object Menu
    }

    object Auth {
        @Serializable
        object Profile
    }
}
