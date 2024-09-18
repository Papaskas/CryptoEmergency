package com.cryptoemergency.cryptoemergency.lib

import android.Manifest
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

object Permissions {
    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    fun media(
        onPermissionResult: (Boolean) -> Unit
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val imageState = rememberPermissionState(
                Manifest.permission.READ_MEDIA_IMAGES,
                onPermissionResult,
            )
            val videoState = rememberPermissionState(
                Manifest.permission.READ_MEDIA_VIDEO,
                onPermissionResult,
            )

            check(listOf(imageState, videoState))
        } else {
            val storageState = rememberPermissionState(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                onPermissionResult,
            )

            check(listOf(storageState))
        }
    }
}

@Composable
@OptIn(ExperimentalPermissionsApi::class)
private fun check(states: List<PermissionState>) {
    SideEffect {
        states.forEach {
            if(!it.status.isGranted) {
                it.launchPermissionRequest()
            }
        }
    }
}
