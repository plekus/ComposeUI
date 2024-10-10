package ru.example.composeui.utils

import java.util.UUID

actual fun uuid(): String = UUID.randomUUID().toString()