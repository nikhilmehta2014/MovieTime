package com.nikhil.movietime.ui.home.domain.repository

import com.nikhil.movietime.ui.home.domain.model.NowPlayingMovie
import com.nikhil.movietime.ui.home.domain.model.TrendingMovie

interface HomeRepository {
    suspend fun getTrendingMovies(): List<TrendingMovie>
    suspend fun getNowPlayingMovies(): List<NowPlayingMovie>
}