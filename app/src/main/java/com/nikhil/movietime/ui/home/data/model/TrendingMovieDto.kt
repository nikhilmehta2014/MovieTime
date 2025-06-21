package com.nikhil.movietime.ui.home.data.model

import com.google.gson.annotations.SerializedName

data class TrendingMoviesResponse(
    @SerializedName("results") val movies: List<TrendingMoviesDto>
)

data class TrendingMoviesDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String?,
)