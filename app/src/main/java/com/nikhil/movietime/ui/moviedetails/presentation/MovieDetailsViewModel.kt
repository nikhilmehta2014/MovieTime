package com.nikhil.movietime.ui.moviedetails.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.movietime.core.data.repository.FavoriteRepository
import com.nikhil.movietime.core.domain.model.Movie
import com.nikhil.movietime.core.network.NetworkMonitor
import com.nikhil.movietime.ui.moviedetails.domain.repository.MovieDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MovieDetailsRepository,
    private val favoriteRepository: FavoriteRepository,
    networkMonitor: NetworkMonitor,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val movieId: Int = checkNotNull(savedStateHandle["movieId"])

    private val _state = MutableStateFlow(MovieDetailsState())
    val state: StateFlow<MovieDetailsState> = _state

    private val _isFavorite = mutableStateOf(false)
    val isFavorite: State<Boolean> = _isFavorite

    init {
        checkFavoriteStatus(movieId)

        viewModelScope.launch {
            networkMonitor.isConnected.collect { isOnline ->
                _state.update { it.copy(isConnected = isOnline) }
                if (isOnline) {
                    repository.refreshMovieDetails(movieId) // Remote → Room
                }
                observeLocalMovieDetails()
            }
        }
    }

    private fun observeLocalMovieDetails() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            repository.getMovieDetails(movieId)
                .catch { e ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = e.localizedMessage ?: "Something went wrong"
                        )
                    }
                }
                .collect { details ->
                    val hasLocalData = details != null
                    _state.update {
                        it.copy(
                            movie = details,
                            isLoading = false,
                            error = null,
                            hasLocalData = hasLocalData,
                        )
                    }
                }
        }
    }

    fun checkFavoriteStatus(movieId: Int) {
        viewModelScope.launch {
            _isFavorite.value = favoriteRepository.isFavorite(movieId)
        }
    }

    fun toggleFavorite(movie: Movie) {
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
}