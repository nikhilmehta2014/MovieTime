package com.nikhil.movietime.core.domain.repository

import com.nikhil.movietime.core.data.local.MovieDao
import com.nikhil.movietime.core.data.repository.FavoriteRepository
import com.nikhil.movietime.core.domain.model.MovieDetails
import com.nikhil.movietime.ui.favorite.data.mapper.toDomain
import com.nikhil.movietime.ui.moviedetails.data.mappers.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val dao: MovieDao
) : FavoriteRepository {

    override suspend fun saveFavorite(movie: MovieDetails) {
        dao.insert(movie.toEntity())
    }

    override suspend fun removeFavorite(movie: MovieDetails) {
        dao.delete(movie.toEntity())
    }

    override suspend fun isFavorite(movieId: Int): Boolean {
        return dao.exists(movieId)
    }

    override suspend fun getFavoriteMovies(): Flow<List<MovieDetails>> {
        return dao.getAll().map { list -> list.map { it.toDomain() } }
    }

    override fun getFavoriteMovieIds(): Flow<Set<Int>> {
        return dao.getAll()
            .map { list -> list.map { it.id }.toSet() }
    }
}