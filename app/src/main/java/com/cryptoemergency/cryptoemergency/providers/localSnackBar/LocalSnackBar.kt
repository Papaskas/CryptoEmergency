package com.cryptoemergency.cryptoemergency.providers.localSnackBar

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.staticCompositionLocalOf

val LocalSnackBar =
    staticCompositionLocalOf<SnackbarHostState> { error("No localSnackBar provided") }
