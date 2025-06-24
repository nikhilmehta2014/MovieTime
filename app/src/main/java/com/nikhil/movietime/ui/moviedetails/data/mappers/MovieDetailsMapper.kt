package com.nikhil.movietime.ui.moviedetails.data.mappers

import com.nikhil.movietime.core.data.local.entities.FavoriteMovieEntity
import com.nikhil.movietime.core.data.local.entities.MovieDetailsEntity
import com.nikhil.movietime.ui.moviedetails.data.model.MovieDetailsDto
import com.nikhil.movietime.core.domain.model.Movie

fun Movie.toEntity(): FavoriteMovieEntity {
    return FavoriteMovieEntity(
        id = id,
        title = title,
        posterUrl = posterUrl,
        releaseYear = releaseYear,
        rating = rating
    )
}

fun MovieDetailsDto.toEntity(): MovieDetailsEntity {
    return MovieDetailsEntity(
        id = id,
        title = title,
        tagline = tagline,
        overview = overview,
        posterUrl = posterPath,
        backdropUrl = backdropPath,
        rating = voteAverage.roundTo1Decimal(),
        runtime = formatRuntime(runtime.toInt()),
        releaseYear = extractYear(releaseDate),
        adult = if (adult) "A" else "U",
        genres = genres.map { it.name }
    )
}

fun MovieDetailsEntity.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        tagline = tagline.orEmpty(),
        overview = overview.orEmpty(),
        posterUrl = posterUrl.orEmpty(),
        backdropUrl = backdropUrl.orEmpty(),
        runtime = runtime.orEmpty(),
        releaseYear = releaseYear.orEmpty(),
        adult = adult.orEmpty(),
        rating = rating ?: 0F,
        genres = genres
    )
}

private fun formatRuntime(runtimeInMinutes: Int): String {
    return if (runtimeInMinutes == 0) {
        ""
    } else {
        val hours = runtimeInMinutes / 60
        val minutes = runtimeInMinutes % 60
        "$hours h $minutes m"
    }
}

private fun extractYear(releaseDate: String?): String {
    return releaseDate?.takeIf { it.length >= 4 }?.substring(0, 4).orEmpty()
}

fun Float.roundTo1Decimal(): Float {
    return (this * 10).toInt().let { whole ->
        val nextDigit = ((this * 100).toInt() % 10)
        if (nextDigit > 5) (whole + 1) / 10f else whole / 10f
    }
}