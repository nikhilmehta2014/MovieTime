package com.nikhil.movietime.ui.home.presentation

import com.nikhil.movietime.core.domain.model.Movie

data class HomeState(
    val trending: List<Movie> = emptyList(),
    val nowPlaying: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isConnected: Boolean = true,
    val hasLocalData: Boolean = false
)