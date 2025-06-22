package com.nikhil.movietime.core.data.repository

import com.nikhil.movietime.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun saveFavorite(movie: Movie)
    suspend fun removeFavorite(movie: Movie)
    suspend fun isFavorite(movieId: Int): Boolean
    suspend fun getFavoriteMovies(): Flow<List<Movie>>
    fun getFavoriteMovieIds(): Flow<Set<Int>>
}