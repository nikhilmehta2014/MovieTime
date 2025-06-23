package com.nikhil.movietime.core.data.mapper

import com.nikhil.movietime.core.data.local.entities.HomeMovieEntity
import com.nikhil.movietime.core.data.model.MovieDto
import com.nikhil.movietime.core.domain.model.Movie

fun MovieDto.toEntity(isTrending: Boolean, isNowPlaying: Boolean): HomeMovieEntity {
    return HomeMovieEntity(
        id = id,
        title = title,
        posterUrl = "https://image.tmdb.org/t/p/w500$posterPath",
        isTrending = isTrending,
        isNowPlaying = isNowPlaying,
        lastUpdated = System.currentTimeMillis()
    )
}

fun HomeMovieEntity.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        posterUrl = posterUrl.orEmpty(),
    )
}
