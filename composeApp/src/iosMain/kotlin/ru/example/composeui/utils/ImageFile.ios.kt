package ru.example.composeui.utils

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import org.jetbrains.skia.Image
import platform.Foundation.NSData
import platform.UIKit.UIImage
import platform.UIKit.UIImageJPEGRepresentation
import platform.posix.memcpy

actual typealias ImageFile = UIImage

actual fun ImageFile.toByteArray() =
    UIImageJPEGRepresentation(this, compressionQuality = 0.5)?.toByteArray() ?: emptyArray<Byte>().toByteArray()

actual fun ImageFile.toImageBitmap(): ImageBitmap? {
    val byteArray = toByteArray()
    return if (byteArray.isNotEmpty()) {
        Image.makeFromEncoded(byteArray).toComposeImageBitmap()
    } else {
        null
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun NSData.toByteArray(): ByteArray =
    ByteArray(length.toInt()).apply {
        usePinned {
            memcpy(it.addressOf(0), bytes, length)
        }
    }