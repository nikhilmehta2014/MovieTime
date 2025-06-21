package com.nikhil.movietime.ui.home.data.mappers

import com.nikhil.movietime.ui.home.data.model.NowPlayingMoviesDto
import com.nikhil.movietime.ui.home.data.model.TrendingMoviesDto
import com.nikhil.movietime.ui.home.domain.model.NowPlayingMovie
import com.nikhil.movietime.ui.home.domain.model.TrendingMovie

fun TrendingMoviesDto.toDomain(): TrendingMovie {
    return TrendingMovie(
        id = id,
        title = title,
        posterUrl = "https://image.tmdb.org/t/p/w500$posterPath",
    )
}

fun NowPlayingMoviesDto.toDomain(): NowPlayingMovie {
    return NowPlayingMovie(
        id = id,
        title = title,
        posterUrl = "https://image.tmdb.org/t/p/w500$posterPath",
    )
}