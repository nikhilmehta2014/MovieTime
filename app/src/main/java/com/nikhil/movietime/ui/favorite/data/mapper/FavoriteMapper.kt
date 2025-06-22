package com.nikhil.movietime.ui.favorite.data.mapper

import com.nikhil.movietime.core.data.local.entities.FavoriteMovieEntity
import com.nikhil.movietime.core.domain.model.Movie

fun FavoriteMovieEntity.toDomain(): Movie {
    return Movie(
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