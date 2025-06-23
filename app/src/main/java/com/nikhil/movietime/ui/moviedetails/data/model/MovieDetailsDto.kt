package com.nikhil.movietime.ui.moviedetails.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsDto(
    @SerializedName("id") val id: Int,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("title") val title: String,
    @SerializedName("tagline") val tagline: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("status") val status: String,
    @SerializedName("runtime") val runtime: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Float,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("genres") val genres: List<Genres>,
)

data class Genres(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
)