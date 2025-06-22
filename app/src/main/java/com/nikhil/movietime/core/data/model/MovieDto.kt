package com.nikhil.movietime.core.data.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("results") val movies: List<MovieDto>
)

data class MovieDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String?,
)