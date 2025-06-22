package com.nikhil.movietime.ui.moviedetails.presentation

import com.nikhil.movietime.core.domain.model.Movie

data class MovieDetailsState(
    val isLoading: Boolean = false,
    val movie: Movie? = null,
    val error: String? = null,
    val isConnected: Boolean = true,
    val hasLocalData: Boolean = false
)