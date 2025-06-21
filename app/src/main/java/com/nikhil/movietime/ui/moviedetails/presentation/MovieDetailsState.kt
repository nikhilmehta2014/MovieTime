package com.nikhil.movietime.ui.moviedetails.presentation

import com.nikhil.movietime.ui.moviedetails.domain.model.MovieDetails

data class MovieDetailsState(
    val isLoading: Boolean = false,
    val movie: MovieDetails? = null,
    val error: String? = null
)