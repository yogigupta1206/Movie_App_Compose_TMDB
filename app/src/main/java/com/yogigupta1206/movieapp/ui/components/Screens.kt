package com.yogigupta1206.movieapp.ui.components


sealed class Screens(val route: String) {
    data object HomePage : Screens("homepage")
    data object MovieDetailsPage : Screens("movieDetails")
}