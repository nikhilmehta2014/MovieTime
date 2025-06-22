package com.nikhil.movietime.ui.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.movietime.core.data.repository.FavoriteRepository
import com.nikhil.movietime.core.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _favoriteMovies = MutableStateFlow<List<Movie>>(emptyList())
    val favoriteMovies: StateFlow<List<Movie>> = _favoriteMovies.asStateFlow()

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() {
        viewModelScope.launch {
            favoriteRepository.getFavoriteMovies()
                .collect { movies ->
                    _favoriteMovies.value = movies
                }
        }
    }

    fun removeFavoriteFromDatabase(movie: Movie){
        viewModelScope.launch {
            favoriteRepository.removeFavorite(movie)
        }
    }
}