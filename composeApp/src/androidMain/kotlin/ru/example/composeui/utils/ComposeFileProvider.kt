package ru.example.composeui.utils

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import ru.example.composeui.R
import java.io.File
import java.util.Objects

class ComposeFileProvider :
    FileProvider(
        R.xml.file_paths,
    ) {
    companion object {
        fun getImageUri(context: Context): Uri {
            // 1
            val tempFile =
                File
                    .createTempFile(
                        "picture_${System.currentTimeMillis()}",
                        ".png",
                        getCacheDir(context),
                    ).apply {
                        createNewFile()
                    }
            // 2
            val authority = context.applicationContext.packageName + ".provider"
            // 3
            println("getImageUri: ${tempFile.absolutePath}")
            return getUriForFile(
                Objects.requireNonNull(context),
                authority,
                tempFile,
            )
        }

        private fun getCacheDir(context: Context): File {
            val fileDir = File(context.cacheDir, "cache_images")
            if (!fileDir.exists()) {
                fileDir.mkdir()
            }
            return fileDir
        }
    }
}