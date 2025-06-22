package com.nikhil.movietime.ui.favorite.data.mapper

import com.nikhil.movietime.core.data.local.entities.MovieEntity
import com.nikhil.movietime.core.domain.model.MovieDetails

fun MovieEntity.toDomain(): MovieDetails {
    return MovieDetails(
        id = id,
        title = title,
        tagline = "",
        overview = overview,
        status = "",
        posterUrl = posterUrl,
        backdropUrl = backdropUrl,
        runtime = "",
        releaseYear = releaseYear,
        adult = adult,
        rating = 0F,
        genres = genres
    )
}