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

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    fun images(
        onPermissionResult: (Boolean) -> Unit
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val imageState = rememberPermissionState(
                Manifest.permission.READ_MEDIA_IMAGES,
                onPermissionResult,
            )

            check(listOf(imageState))
        } else {
            val storageState = rememberPermissionState(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                onPermissionResult,
            )

            check(listOf(storageState))
        }
    }

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    fun video(
        onPermissionResult: (Boolean) -> Unit
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val videoState = rememberPermissionState(
                Manifest.permission.READ_MEDIA_VIDEO,
                onPermissionResult,
            )

            check(listOf(videoState))
        } else {
            val storageState = rememberPermissionState(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                onPermissionResult,
            )

            check(listOf(storageState))
        }
    }

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    fun camera(
        onPermissionResult: (Boolean) -> Unit
    ) {
        val state = rememberPermissionState(
            Manifest.permission.CAMERA,
            onPermissionResult,
        )

        check(listOf(state))
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
