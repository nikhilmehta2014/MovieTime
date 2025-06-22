package com.nikhil.movietime.core.domain.repository

import com.nikhil.movietime.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getTrendingMovies(): Flow<List<Movie>>
    fun getNowPlayingMovies(): Flow<List<Movie>>
    suspend fun refreshHomeMovies()
}
