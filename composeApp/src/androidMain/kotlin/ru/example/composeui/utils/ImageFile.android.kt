package ru.example.composeui.utils

import android.content.ContentResolver
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

actual typealias ImageFile = ImageUri

actual fun ImageFile.toByteArray() =
    contentResolver.openInputStream(uri)?.use {
        it.readBytes()
    } ?: throw IllegalStateException("Couldn't open inputStream $uri")

actual fun ImageFile.toImageBitmap(): ImageBitmap? {
    val byteArray = toByteArray()
    return if (byteArray.isNotEmpty()) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size).asImageBitmap()
    } else {
        null
    }
}

class ImageUri(
    val uri: Uri,
    val contentResolver: ContentResolver,
)