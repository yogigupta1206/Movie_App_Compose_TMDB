package com.yogigupta1206.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class MovieApiEntity(
    val page: Int = -1,
    val results: List<MovieEntity> = emptyList()
)

data class MovieEntity (
    val id: Int = -1,
    val title: String = "",
    val overview: String = "",
    @SerializedName("poster_path") var posterPath: String = ""
)