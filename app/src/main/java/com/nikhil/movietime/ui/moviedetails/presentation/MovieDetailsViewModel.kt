package com.nikhil.movietime.ui.moviedetails.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.movietime.core.data.repository.FavoriteRepository
import com.nikhil.movietime.core.domain.model.MovieDetails
import com.nikhil.movietime.ui.moviedetails.domain.repository.MovieDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MovieDetailsRepository,
    private val favoriteRepository: FavoriteRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailsState())
    val state: StateFlow<MovieDetailsState> = _state

    // TODO - See if it can be merged with "MovieDetailsState"
    private val _isFavorite = mutableStateOf(false)
    val isFavorite: State<Boolean> = _isFavorite

    init {
        savedStateHandle.get<Int>("movieId")?.let { movieId ->
            fetchMovieDetails(movieId)
        }
    }

    fun checkFavoriteStatus(movieId: Int) {
        viewModelScope.launch {
            _isFavorite.value = favoriteRepository.isFavorite(movieId)
        }
    }

    fun toggleFavorite(movie: MovieDetails) {
        viewModelScope.launch {
            val currentlyFavorite = isFavorite.value
            if (currentlyFavorite) {
                favoriteRepository.removeFavorite(movie)
            } else {
                favoriteRepository.saveFavorite(movie)
            }
            _isFavorite.value = !currentlyFavorite
        }
    }

    private fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(isLoading = true)
                val result = repository.getMovieDetails(movieId)
                _state.value = _state.value.copy(
                    isLoading = false,
                    movie = result,
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Failed to load movie details"
                )
            }
        }
    }
}