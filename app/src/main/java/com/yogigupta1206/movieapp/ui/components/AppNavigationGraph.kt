package com.yogigupta1206.movieapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigationGraph() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.HomePage.route,
        modifier = Modifier
    ) {

        composable(Screens.HomePage.route) {
            //HomeScreen(onNavigateToMovieDetails = { navController.onNavigateToMovieDetails(it) })
        }

        composable(
            route = Screens.MovieDetailsPage.route + "?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
           // MovieDetailsScreen(onNavigateBack = { navController.popBackStack() })
        }


    }
}

fun NavController.onNavigateToMovieDetails(id: String) =
    navigate(Screens.MovieDetailsPage.route + "?id=$id")

