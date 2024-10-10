package ru.example.composeui.utils

import androidx.compose.ui.graphics.ImageBitmap

expect class ImageFile

expect fun ImageFile.toByteArray(): ByteArray

expect fun ImageFile.toImageBitmap(): ImageBitmap?