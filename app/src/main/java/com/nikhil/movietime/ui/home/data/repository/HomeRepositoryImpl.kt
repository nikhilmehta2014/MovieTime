package com.nikhil.movietime.ui.home.data.repository

import com.nikhil.movietime.core.network.ApiService
import com.nikhil.movietime.ui.home.data.mappers.toDomain
import com.nikhil.movietime.ui.home.data.model.NowPlayingMoviesDto
import com.nikhil.movietime.ui.home.data.model.TrendingMoviesDto
import com.nikhil.movietime.ui.home.domain.model.NowPlayingMovie
import com.nikhil.movietime.ui.home.domain.model.TrendingMovie
import com.nikhil.movietime.ui.home.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val api: ApiService
) : HomeRepository {

    override suspend fun getTrendingMovies(): List<TrendingMovie> {
        val dtoList: List<TrendingMoviesDto> = api.getTrendingMovies(timeWindow = "day").movies
        return dtoList.map { it.toDomain() }
    }

    override suspend fun getNowPlayingMovies(): List<NowPlayingMovie> {
        val dtoList: List<NowPlayingMoviesDto> = api.getNowPlayingMovies().movies
        return dtoList.map { it.toDomain() }
    }
}
