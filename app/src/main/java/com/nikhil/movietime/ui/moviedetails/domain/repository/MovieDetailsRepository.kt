package com.nikhil.movietime.ui.moviedetails.domain.repository

import com.nikhil.movietime.ui.moviedetails.domain.model.MovieDetails

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int): MovieDetails
    suspend fun saveFavorite(movie: MovieDetails)
    suspend fun removeFavorite(movie: MovieDetails)
    suspend fun isFavorite(movieId: Int): Boolean
}