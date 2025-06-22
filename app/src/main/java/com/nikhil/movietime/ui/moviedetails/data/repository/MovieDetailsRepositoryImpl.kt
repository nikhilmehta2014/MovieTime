package com.nikhil.movietime.ui.moviedetails.data.repository

import com.nikhil.movietime.core.data.local.MovieDao
import com.nikhil.movietime.core.network.ApiService
import com.nikhil.movietime.ui.moviedetails.data.mappers.toDomain
import com.nikhil.movietime.ui.moviedetails.data.mappers.toEntity
import com.nikhil.movietime.ui.moviedetails.data.model.MovieDetailsDto
import com.nikhil.movietime.ui.moviedetails.domain.model.MovieDetails
import com.nikhil.movietime.ui.moviedetails.domain.repository.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dao: MovieDao
) : MovieDetailsRepository {

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val movieDetails: MovieDetailsDto = api.getMovieDetails(movieId = movieId)
        return movieDetails.toDomain()
    }

    override suspend fun saveFavorite(movie: MovieDetails) {
        dao.insert(movie.toEntity())
    }

    override suspend fun removeFavorite(movie: MovieDetails) {
        dao.delete(movie.toEntity())
    }

    override suspend fun isFavorite(movieId: Int): Boolean {
        return dao.exists(movieId)
    }
}