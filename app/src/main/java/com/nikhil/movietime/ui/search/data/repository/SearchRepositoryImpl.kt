package com.nikhil.movietime.ui.search.data.repository

import com.nikhil.movietime.core.network.ApiService
import com.nikhil.movietime.ui.search.data.mapper.toDomain
import com.nikhil.movietime.ui.search.data.model.SearchedMovieDto
import com.nikhil.movietime.ui.search.domain.model.SearchedMovie
import com.nikhil.movietime.ui.search.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val api: ApiService
) : SearchRepository {

    override suspend fun getMovies(query: String): List<SearchedMovie> {
        val searchedMovies: List<SearchedMovieDto> = api.searchMovies(query = query).movies
        return searchedMovies.map { it.toDomain() }
    }
}
