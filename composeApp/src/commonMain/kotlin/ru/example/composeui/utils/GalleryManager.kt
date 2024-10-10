package ru.example.composeui.utils

import androidx.compose.runtime.Composable

@Composable
expect fun rememberGalleryManager(onResult: (ImageFile?) -> Unit): GalleryManager


expect class GalleryManager(
    onLaunch: () -> Unit
) {
    fun launch()
}