package ru.example.composeui.data

actual class PlatformConfiguration constructor(private val userAgent: String, private val version: String, private val code: String) {
    actual fun userAgentName(): String = userAgent
    actual fun appName(): String = "composeui"
    actual fun versionName(): String = version
    actual fun versionCode(): String = code
}