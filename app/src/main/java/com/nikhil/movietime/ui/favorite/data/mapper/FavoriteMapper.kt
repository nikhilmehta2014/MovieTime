package com.nikhil.movietime.ui.favorite.data.mapper

import com.nikhil.movietime.core.data.local.entities.FavoriteMovieEntity
import com.nikhil.movietime.core.domain.model.Movie

fun FavoriteMovieEntity.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        posterUrl = posterUrl.orEmpty(),
        releaseYear = releaseYear.orEmpty(),
        rating = rating ?: 0F
    )
}