package ru.example.composeui

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import platform.UIKit.UIWindow
import ru.example.composeui.theme.AppTheme
import ru.example.composeui.ui.RootScreen
import ru.example.composeui.ui.base.ProvideSafeArea

fun mainController(window: UIWindow): UIViewController =
    ComposeUIViewController {
        AppTheme {
            ProvideSafeArea(window) {
                RootScreen()
            }
        }
    }