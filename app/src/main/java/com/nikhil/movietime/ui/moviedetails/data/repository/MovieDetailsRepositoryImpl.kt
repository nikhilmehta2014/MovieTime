package com.nikhil.movietime.ui.moviedetails.data.repository

import com.nikhil.movietime.core.data.local.MovieDao
import com.nikhil.movietime.core.domain.model.MovieDetails
import com.nikhil.movietime.core.network.ApiService
import com.nikhil.movietime.ui.moviedetails.data.mappers.toDomain
import com.nikhil.movietime.ui.moviedetails.data.model.MovieDetailsDto
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
}