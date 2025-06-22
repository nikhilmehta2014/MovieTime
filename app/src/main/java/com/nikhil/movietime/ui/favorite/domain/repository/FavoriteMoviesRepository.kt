package com.nikhil.movietime.ui.favorite.domain.repository

import com.nikhil.movietime.core.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface FavoriteMoviesRepository {
    suspend fun getFavoriteMovies(): Flow<List<MovieDetails>>
}