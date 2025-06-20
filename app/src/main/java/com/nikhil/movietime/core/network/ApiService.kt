package com.nikhil.movietime.core.network

import com.nikhil.movietime.ui.home.data.model.TrendingMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("trending/movie/{time_window}")
    suspend fun getTrendingMovies(
        @Query("api_key") apiKey: String,
        @Path("time_window") movieId: String
    ): TrendingMoviesResponse
}