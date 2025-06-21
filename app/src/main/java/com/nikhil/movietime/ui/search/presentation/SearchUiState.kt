package com.nikhil.movietime.ui.search.presentation

import com.nikhil.movietime.ui.search.domain.model.SearchedMovie

data class SearchUiState(
    val query: String = "",
    val isLoading: Boolean = false,
    val movies: List<SearchedMovie> = emptyList(),
    val errorMessage: String? = null
)
