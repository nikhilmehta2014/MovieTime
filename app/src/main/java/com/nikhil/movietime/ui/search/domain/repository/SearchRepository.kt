package com.nikhil.movietime.ui.search.domain.repository

import com.nikhil.movietime.ui.search.domain.model.SearchedMovie

interface SearchRepository {
    suspend fun getMovies(query: String): List<SearchedMovie>
}