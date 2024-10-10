package ru.example.composeui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import ru.example.composeui.ui.main.MainScreen

private const val MAIN = "main"

fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.MAIN,
        startDestination = MAIN,
    ) {
        composable(route = MAIN) {
            MainScreen(
                navController = navController
            )
        }
    }
}