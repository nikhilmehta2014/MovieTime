package com.nikhil.movietime.ui.moviedetails.domain.repository

import com.nikhil.movietime.core.domain.model.Movie

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int): Movie
}