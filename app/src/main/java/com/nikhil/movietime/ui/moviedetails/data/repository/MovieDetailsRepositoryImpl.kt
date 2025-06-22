package com.nikhil.movietime.ui.moviedetails.data.repository

import android.util.Log
import com.nikhil.movietime.core.data.local.MovieDao
import com.nikhil.movietime.core.domain.model.Movie
import com.nikhil.movietime.core.network.ApiService
import com.nikhil.movietime.ui.moviedetails.data.mappers.toDomain
import com.nikhil.movietime.ui.moviedetails.data.mappers.toEntity
import com.nikhil.movietime.ui.moviedetails.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dao: MovieDao
) : MovieDetailsRepository {

    override suspend fun getMovieDetails(movieId: Int): Flow<Movie> {
        return dao.getMovieDetails(movieId)
            .map { it.toDomain() }
    }

    override suspend fun refreshMovieDetails(movieId: Int) {
        try {
            val movieDetailsDto = api.getMovieDetails(movieId = movieId)
            dao.insertDetails(movieDetailsDto.toEntity())
        } catch (e: Exception) {
            val error = e.message ?: "Something went wrong"
            Log.d("asdf", "error=$error")
        }
    }
}