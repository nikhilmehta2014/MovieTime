package com.nikhil.movietime.ui.moviedetails.domain.model

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