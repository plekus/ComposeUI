package ru.example.composeui.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import ru.example.composeui.navigation.RootNavigationGraph

@OptIn(ExperimentalCoilApi::class)
@Composable
fun RootScreen() = Box(modifier = Modifier.fillMaxSize()) {

    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context).crossfade(true).logger(DebugLogger()).build()
    }

    val navController: NavHostController = rememberNavController()

    RootNavigationGraph(navController = navController)
}