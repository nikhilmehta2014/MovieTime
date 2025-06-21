package com.nikhil.movietime.ui.home.data.model

import com.google.gson.annotations.SerializedName

data class NowPlayingMoviesResponse(
    @SerializedName("results") val movies: List<NowPlayingMoviesDto>
)

data class NowPlayingMoviesDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String?,
)