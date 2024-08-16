package com.yogigupta1206.movieapp.domain.repository

import com.yogigupta1206.movieapp.domain.model.MovieApiEntity
import com.yogigupta1206.movieapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepo {
    fun getMovieData(): Flow<Resource<MovieApiEntity>>
}