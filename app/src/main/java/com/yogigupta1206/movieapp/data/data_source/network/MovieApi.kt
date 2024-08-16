package com.yogigupta1206.movieapp.data.data_source.network

import com.yogigupta1206.movieapp.domain.model.MovieApiEntity
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {

    @GET("3/trending/movie/day?language=en-US")
    suspend fun getMovieData(): Response<MovieApiEntity>

}