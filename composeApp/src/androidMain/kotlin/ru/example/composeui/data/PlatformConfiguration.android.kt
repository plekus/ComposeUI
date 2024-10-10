package ru.example.composeui.data

import android.content.Context

actual class PlatformConfiguration constructor(
    val androidContext: Context,
    private val version: String,
    private val code: String,
) {
    actual fun userAgentName(): String = System.getProperty("http.agent")

    actual fun appName(): String = "composeui"

    actual fun versionName(): String = version

    actual fun versionCode(): String = code
}