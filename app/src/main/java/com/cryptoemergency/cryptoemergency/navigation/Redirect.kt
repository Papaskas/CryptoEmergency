package com.cryptoemergency.cryptoemergency.navigation

/**
 *
 * */
data class Redirect(
    var route: Destination,
    var popBackStack: Boolean = false,
)
