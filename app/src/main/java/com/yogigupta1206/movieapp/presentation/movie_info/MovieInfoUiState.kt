package com.yogigupta1206.movieapp.presentation.movie_info

import com.yogigupta1206.movieapp.domain.model.MovieEntity

data class MovieInfoUiState(
    val movie: MovieEntity? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
