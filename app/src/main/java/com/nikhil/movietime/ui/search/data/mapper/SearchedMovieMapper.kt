package com.nikhil.movietime.ui.search.data.mapper

import com.nikhil.movietime.core.domain.model.MovieDetails
import com.nikhil.movietime.ui.search.data.model.SearchedMovieDto
import com.nikhil.movietime.ui.search.domain.model.SearchedMovie

fun SearchedMovieDto.toDomain(): SearchedMovie {
    return SearchedMovie(
        id = id,
        title = title,
        posterUrl = "https://image.tmdb.org/t/p/w500$posterPath",
        releaseYear = extractYear(releaseDate),
        rating = voteAverage.roundTo1Decimal()
    )
}

fun SearchedMovie.toMovie(): MovieDetails{
    return MovieDetails(
        id = id,
        title = title,
        tagline = "",
        overview = "",
        status = "",
        posterUrl = posterUrl,
        backdropUrl = "",
        runtime = "",
        releaseYear = releaseYear ?: "NA",
        adult = "",
        rating = rating,
        genres = emptyList()
    )
}

private fun extractYear(releaseDate: String?): String {
    return releaseDate?.takeIf { it.length >= 4 }?.substring(0, 4).orEmpty()
}

//TODO - should i move it to utils if it is being duplicated?
fun Float.roundTo1Decimal(): Float {
    return (this * 10).toInt().let { whole ->
        val nextDigit = ((this * 100).toInt() % 10)
        if (nextDigit > 5) (whole + 1) / 10f else whole / 10f
    }
}