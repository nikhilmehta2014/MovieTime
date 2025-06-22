package com.nikhil.movietime.core.domain.model

// TODO - Rename it to "Movie" and "Movie" to "HomeMovie" or some other apt name
data class MovieDetails(
    val id: Int,
    val title: String,
    val tagline: String,
    val overview: String,
    val status: String,
    val posterUrl: String,
    val backdropUrl: String,
    val runtime: String,
    val releaseYear: String,
    val adult: String,
    val genres: List<String>,
)