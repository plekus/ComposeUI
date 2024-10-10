package ru.example.composeui

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform