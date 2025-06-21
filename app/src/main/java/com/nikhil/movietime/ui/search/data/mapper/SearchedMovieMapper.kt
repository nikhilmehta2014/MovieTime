package com.nikhil.movietime.ui.search.data.mapper

import com.nikhil.movietime.ui.search.data.model.SearchedMovieDto
import com.nikhil.movietime.ui.search.domain.model.SearchedMovie

fun SearchedMovieDto.toDomain(): SearchedMovie {
    return SearchedMovie(
        id = id,
        title = title,
        posterUrl = "https://image.tmdb.org/t/p/w500$posterPath",
        releaseYear = extractYear(releaseDate),
    )
}

private fun extractYear(releaseDate: String?): String {
    return releaseDate?.takeIf { it.length >= 4 }?.substring(0, 4).orEmpty()
}