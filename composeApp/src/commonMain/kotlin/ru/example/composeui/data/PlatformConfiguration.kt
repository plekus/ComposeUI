package ru.example.composeui.data

expect class PlatformConfiguration {
    fun userAgentName() : String
    fun appName() : String
    fun versionName() : String
    fun versionCode() : String
}