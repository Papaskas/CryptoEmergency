package com.cryptoemergency.cryptoemergency.ui.common.screens

data class Redirect(
    var route: String,
    var popBackStack: Boolean = false,
)
