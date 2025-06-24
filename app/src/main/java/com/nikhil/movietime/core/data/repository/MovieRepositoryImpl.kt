package com.nikhil.movietime.core.data.repository

import com.nikhil.movietime.core.data.local.MovieDao
import com.nikhil.movietime.core.data.mapper.toDomain
import com.nikhil.movietime.core.data.mapper.toEntity
import com.nikhil.movietime.core.domain.model.Movie
import com.nikhil.movietime.core.domain.repository.MovieRepository
import com.nikhil.movietime.core.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dao: MovieDao
) : MovieRepository {

    override fun getTrendingMovies(): Flow<List<Movie>> = flow {
        val local = dao.getTrendingMovies().map { it.map { it.toDomain() } }
        emitAll(local)

        try {
            val remote = api.getTrendingMovies(timeWindow = "day").movies
            dao.clearHomeMovies()
            dao.insertAll(remote.map { it.toEntity(isTrending = true, isNowPlaying = false) })
        } catch (_: Exception) {
            // no-op
        }
    }

    override fun getNowPlayingMovies(): Flow<List<Movie>> = flow {
        val local = dao.getNowPlayingMovies().map { it.map { it.toDomain() } }
        emitAll(local)

        try {
            val remote = api.getNowPlayingMovies().movies
            dao.insertAll(remote.map { it.toEntity(isTrending = false, isNowPlaying = true) })
        } catch (_: Exception) {
            // no-op
        }
    }

    override suspend fun refreshHomeMovies() {
        try {
            val trending = api.getTrendingMovies("day").movies
            val nowPlaying = api.getNowPlayingMovies().movies

            dao.clearHomeMovies()
            dao.insertAll(trending.map { it.toEntity(isTrending = true, isNowPlaying = false) })
            dao.insertAll(nowPlaying.map { it.toEntity(isTrending = false, isNowPlaying = true) })

        } catch (_: Exception) {}
    }

}
