package com.nikhil.movietime.ui.home.domain.repository

import com.nikhil.movietime.ui.home.domain.model.TrendingMovies

interface HomeRepository {
    suspend fun getTrendingMovies(): List<TrendingMovies>
}