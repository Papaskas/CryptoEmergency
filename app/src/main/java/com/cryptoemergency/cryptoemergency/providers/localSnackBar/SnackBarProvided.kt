package com.cryptoemergency.cryptoemergency.providers.localSnackBar

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember

@Composable
fun SnackBarProvider(content: @Composable () -> Unit) {
    val snackBarHostState = remember { SnackbarHostState() }

    CompositionLocalProvider(LocalSnackBar provides snackBarHostState) {
        content()
    }
}
