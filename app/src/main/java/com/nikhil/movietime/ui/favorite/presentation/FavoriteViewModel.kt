package com.nikhil.movietime.ui.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.movietime.core.domain.model.MovieDetails
import com.nikhil.movietime.ui.favorite.domain.repository.FavoriteMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteMoviesRepository: FavoriteMoviesRepository
) : ViewModel() {

    private val _favoriteMovies = MutableStateFlow<List<MovieDetails>>(emptyList())
    val favoriteMovies: StateFlow<List<MovieDetails>> = _favoriteMovies.asStateFlow()

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() {
        viewModelScope.launch {
            favoriteMoviesRepository.getFavoriteMovies()
                .collect { movies ->
                    _favoriteMovies.value = movies
                }
        }
    }
}