package com.nikhil.movietime.ui.moviedetails.data.mappers

import com.nikhil.movietime.ui.moviedetails.data.model.MovieDetailsDto
import com.nikhil.movietime.ui.moviedetails.domain.model.MovieDetails

fun MovieDetailsDto.toDomain(): MovieDetails {
    return MovieDetails(
        id = id,
        title = title,
        tagline = tagline,
        overview = overview,
        status = status,
        posterUrl = "https://image.tmdb.org/t/p/w500$posterPath",
        backdropUrl = "https://image.tmdb.org/t/p/w500$backdropPath",
    )
}