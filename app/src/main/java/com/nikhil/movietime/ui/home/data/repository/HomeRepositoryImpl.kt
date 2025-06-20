package com.nikhil.movietime.ui.home.data.repository

import com.nikhil.movietime.core.network.ApiService
import com.nikhil.movietime.ui.home.data.mappers.toDomain
import com.nikhil.movietime.ui.home.data.model.TrendingMoviesDto
import com.nikhil.movietime.ui.home.domain.model.TrendingMovies
import com.nikhil.movietime.ui.home.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val api: ApiService
) : HomeRepository {

    override suspend fun getTrendingMovies(): List<TrendingMovies> {
        val dtoList: List<TrendingMoviesDto> = api.getTrendingMovies("", "").movies
        return dtoList.map { it.toDomain() }
    }


}
