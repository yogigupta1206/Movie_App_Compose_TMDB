package com.yogigupta1206.movieapp.presentation.home

import com.yogigupta1206.movieapp.domain.model.MovieEntity

data class HomeScreenUiState(
    val movieData: List<MovieEntity> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
