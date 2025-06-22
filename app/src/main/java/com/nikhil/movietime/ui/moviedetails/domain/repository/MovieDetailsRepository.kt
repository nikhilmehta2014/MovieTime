package com.nikhil.movietime.ui.moviedetails.domain.repository

import com.nikhil.movietime.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int): Flow<Movie>
    suspend fun refreshMovieDetails(movieId: Int)
}