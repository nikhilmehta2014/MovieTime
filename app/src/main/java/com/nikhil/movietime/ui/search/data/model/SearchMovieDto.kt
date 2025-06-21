package com.nikhil.movietime.ui.search.data.model

import com.google.gson.annotations.SerializedName

data class SearchedMoviesResponse(
    @SerializedName("results") val movies: List<SearchedMovieDto>
)

data class SearchedMovieDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Float,
)