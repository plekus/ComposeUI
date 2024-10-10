package ru.example.composeui.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex

@Immutable
data class SafeAreaBehavior(
    val extendToTop: Boolean = false,
    val extendToBottom: Boolean = false,
)

@Immutable
data class SafeAreaColors(
    val top: Color = Color.Transparent,
    val bottom: Color = Color.Transparent,
)

@get:Composable
@get:NonRestartableComposable
expect val WindowInsets.Companion.topBar: WindowInsets

@get:Composable
@get:NonRestartableComposable
expect val WindowInsets.Companion.bottomBar: WindowInsets

@Composable
fun SafeArea(
    behavior: SafeAreaBehavior = remember { SafeAreaBehavior() },
    color: SafeAreaColors = remember { SafeAreaColors() },
    content: @Composable () -> Unit,
) {
    Column {
        if (!behavior.extendToTop) {
            Spacer(
                modifier =
                Modifier
                    .windowInsetsTopHeight(WindowInsets.topBar)
                    .zIndex(999F)
                    .fillMaxWidth()
                    .background(color.top),
            )
        }
        Box(
            modifier = Modifier.weight(1f),
        ) {
            content()
        }
        if (!behavior.extendToBottom) {
            Spacer(
                modifier =
                Modifier
                    .windowInsetsBottomHeight(WindowInsets.bottomBar)
                    .zIndex(999F)
                    .fillMaxWidth()
                    .background(color.bottom),
            )
        }
    }
}
