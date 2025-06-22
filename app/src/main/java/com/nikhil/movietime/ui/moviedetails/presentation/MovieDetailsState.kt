package com.nikhil.movietime.ui.moviedetails.presentation

import com.nikhil.movietime.core.domain.model.MovieDetails

data class MovieDetailsState(
    val isLoading: Boolean = false,
    val movie: MovieDetails? = null,
    val error: String? = null
)