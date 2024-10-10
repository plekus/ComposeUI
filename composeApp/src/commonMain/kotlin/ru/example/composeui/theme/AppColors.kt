package ru.example.composeui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Stable
class AppColors(
    val primary: Color,
    val white: Color,
    val primary50: Color,
    val secondary: Color,
)

@Composable
fun appLightColors() = AppColors(
    primary = Color(0xFF291E56),
    primary50 = Color(0xFFE8E8F1),
    secondary = Color(0xFF851661),
    white = Color.White,
)

internal val LocalAppColors = staticCompositionLocalOf<AppColors> {
    error("CompositionLocal LocalColors not present")
}