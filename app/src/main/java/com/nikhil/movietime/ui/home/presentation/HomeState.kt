package com.nikhil.movietime.ui.home.presentation

import com.nikhil.movietime.ui.home.domain.model.NowPlayingMovie
import com.nikhil.movietime.ui.home.domain.model.TrendingMovie

data class HomeState(
    val trending: List<TrendingMovie> = emptyList(),
    val nowPlaying: List<NowPlayingMovie> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isConnected: Boolean = true
)