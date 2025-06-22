package com.nikhil.movietime.ui.favorite.data.repository

import com.nikhil.movietime.core.data.local.MovieDao
import com.nikhil.movietime.core.domain.model.MovieDetails
import com.nikhil.movietime.ui.favorite.data.mapper.toDomain
import com.nikhil.movietime.ui.favorite.domain.repository.FavoriteMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteMoviesRepositoryImpl @Inject constructor(
    private val dao: MovieDao
) : FavoriteMoviesRepository {

    override suspend fun getFavoriteMovies(): Flow<List<MovieDetails>> {
        return dao.getAll().map { list -> list.map { it.toDomain() } }
    }

}