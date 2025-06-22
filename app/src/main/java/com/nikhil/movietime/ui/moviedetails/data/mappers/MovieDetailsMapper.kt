package com.nikhil.movietime.ui.moviedetails.data.mappers

import com.nikhil.movietime.core.data.local.entities.MovieEntity
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
        runtime = formatRuntime(runtime.toInt()),
        releaseYear = extractYear(releaseDate),
        adult = if (adult) "A" else "U",
        genres = genres.map { it.name }
    )
}

fun MovieDetails.toEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        overview = overview,
        posterUrl = posterUrl,
        backdropUrl = backdropUrl,
        releaseYear = releaseYear,
        adult = adult,
        genres = genres
    )
}

private fun formatRuntime(runtimeInMinutes: Int): String {
    val hours = runtimeInMinutes / 60
    val minutes = runtimeInMinutes % 60
    return "${hours} h ${minutes} m"
}

private fun extractYear(releaseDate: String?): String {
    return releaseDate?.takeIf { it.length >= 4 }?.substring(0, 4).orEmpty()
}