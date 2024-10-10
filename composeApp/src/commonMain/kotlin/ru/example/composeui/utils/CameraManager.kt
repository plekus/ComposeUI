package ru.example.composeui.utils

import androidx.compose.runtime.Composable

@Composable
expect fun rememberCameraManager(onResult: (ImageFile?) -> Unit): CameraManager


expect class CameraManager(
    onLaunch: () -> Unit
) {
    fun launch()
}