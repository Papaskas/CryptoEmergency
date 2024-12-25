package com.cryptoemergency.cryptoemergency.providers.localNavController

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController

val LocalNavController =
    staticCompositionLocalOf<NavHostController> { error("No localNavController provided") }
