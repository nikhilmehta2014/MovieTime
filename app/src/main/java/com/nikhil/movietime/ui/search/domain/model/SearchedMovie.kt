package com.nikhil.movietime.ui.search.domain.model

data class SearchedMovie(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val releaseYear: String?,
    val rating: Float,
)