package com.nikhil.movietime.core.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val tagline: String = "",
    val overview: String = "",
    val status: String = "",
    val posterUrl: String,
    val backdropUrl: String = "",
    val runtime: String = "",
    val releaseYear: String = "",
    val adult: String = "",
    val rating: Float = 0F,
    val genres: List<String> = emptyList(),
)