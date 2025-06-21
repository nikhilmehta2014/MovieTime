package com.nikhil.movietime.core.network

import com.nikhil.movietime.ui.moviedetails.data.model.MovieDetailsDto
import com.nikhil.movietime.ui.home.data.model.NowPlayingMoviesResponse
import com.nikhil.movietime.ui.home.data.model.TrendingMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("trending/movie/{time_window}")
    suspend fun getTrendingMovies(
        @Path("time_window") timeWindow: String,
    ): TrendingMoviesResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): NowPlayingMoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
    ): MovieDetailsDto
}