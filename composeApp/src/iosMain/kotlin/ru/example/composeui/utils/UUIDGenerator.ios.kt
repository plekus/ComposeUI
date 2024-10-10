package ru.example.composeui.utils

import platform.Foundation.NSUUID

actual fun uuid(): String = NSUUID().UUIDString()