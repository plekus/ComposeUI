package ru.example.composeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.example.composeui.theme.AppTheme
import ru.example.composeui.ui.RootScreen
import ru.example.composeui.utils.AppContext

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppContext.set(this)
        setContent {
            AppTheme {
                RootScreen()
            }
        }
    }
}