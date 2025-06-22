package com.nikhil.movietime.core.network

import com.nikhil.movietime.core.data.model.MoviesResponse
import com.nikhil.movietime.ui.moviedetails.data.model.MovieDetailsDto
import com.nikhil.movietime.ui.search.data.model.SearchedMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("trending/movie/{time_window}")
    suspend fun getTrendingMovies(
        @Path("time_window") timeWindow: String,
    ): MoviesResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): MoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
    ): MovieDetailsDto

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
    ): SearchedMoviesResponse
}