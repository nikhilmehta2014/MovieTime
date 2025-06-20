package com.nikhil.movietime.ui.home.data.mappers

import com.nikhil.movietime.ui.home.data.model.TrendingMoviesDto
import com.nikhil.movietime.ui.home.domain.model.TrendingMovies

fun TrendingMoviesDto.toDomain(): TrendingMovies {
    return TrendingMovies(
        id = id,
        title = title,
        posterUrl = "https://image.tmdb.org/t/p/w500$posterPath",
    )
}