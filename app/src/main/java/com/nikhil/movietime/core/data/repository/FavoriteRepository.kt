package com.nikhil.movietime.core.data.repository

import com.nikhil.movietime.core.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun saveFavorite(movie: MovieDetails)
    suspend fun removeFavorite(movie: MovieDetails)
    suspend fun isFavorite(movieId: Int): Boolean
    suspend fun getFavoriteMovies(): Flow<List<MovieDetails>>
}