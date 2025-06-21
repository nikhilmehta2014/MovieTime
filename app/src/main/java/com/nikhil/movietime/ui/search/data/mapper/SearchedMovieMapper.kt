package com.nikhil.movietime.ui.search.data.mapper

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

private fun extractYear(releaseDate: String?): String {
    return releaseDate?.takeIf { it.length >= 4 }?.substring(0, 4).orEmpty()
}

fun Float.roundTo1Decimal(): Float {
    return (this * 10).toInt().let { whole ->
        val nextDigit = ((this * 100).toInt() % 10)
        if (nextDigit > 5) (whole + 1) / 10f else whole / 10f
    }
}