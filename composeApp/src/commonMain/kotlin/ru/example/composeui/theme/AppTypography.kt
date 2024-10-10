package ru.example.composeui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import composeui.composeapp.generated.resources.Res
import composeui.composeapp.generated.resources.inter_bold
import composeui.composeapp.generated.resources.inter_medium
import composeui.composeapp.generated.resources.inter_regular
import composeui.composeapp.generated.resources.inter_semibold
import org.jetbrains.compose.resources.Font

class AppTypography(family: FontFamily) {
    val button14: TextStyle = TextStyle(
        fontFamily = family,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    )

    val paragraph14: TextStyle = TextStyle(
        fontFamily = family,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 24.sp,
    )
}

@Composable
fun InterFontFamily() = FontFamily(
    Font(resource = Res.font.inter_bold, weight = FontWeight.Bold),
    Font(resource = Res.font.inter_medium, weight = FontWeight.Medium),
    Font(resource = Res.font.inter_semibold, weight = FontWeight.SemiBold),
    Font(resource = Res.font.inter_regular, weight = FontWeight.Normal),
)

internal val LocalAppTypography = staticCompositionLocalOf<AppTypography> {
    error("CompositionLocal LocalSalonTypography not present")
}