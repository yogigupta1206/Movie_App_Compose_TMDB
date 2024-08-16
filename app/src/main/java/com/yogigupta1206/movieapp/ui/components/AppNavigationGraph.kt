package com.yogigupta1206.movieapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yogigupta1206.movieapp.presentation.CommonViewModel
import com.yogigupta1206.movieapp.presentation.home.HomeScreen
import com.yogigupta1206.movieapp.presentation.movie_info.MovieDetailsScreen

@Composable
fun AppNavigationGraph() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.HomePage.route,
        modifier = Modifier
    ) {

        composable(Screens.HomePage.route) {
            HomeScreen(onNavigateToMovieDetails = { navController.onNavigateToMovieDetails() })
        }

        composable(Screens.MovieDetailsPage.route) {
            val backStackEntry = remember(navController) {
                navController.getBackStackEntry(Screens.HomePage.route)
            }
            val viewModel: CommonViewModel = hiltViewModel(backStackEntry)
            MovieDetailsScreen(viewModel, onNavigateBack = { navController.popBackStack() })
        }


    }
}

fun NavController.onNavigateToMovieDetails() =
    navigate(Screens.MovieDetailsPage.route)

