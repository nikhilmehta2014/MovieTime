package com.nikhil.movietime.ui.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.movietime.core.data.repository.FavoriteRepository
import com.nikhil.movietime.core.domain.model.MovieDetails
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

    private val _favoriteMovies = MutableStateFlow<List<MovieDetails>>(emptyList())
    val favoriteMovies: StateFlow<List<MovieDetails>> = _favoriteMovies.asStateFlow()

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

    // TODO - improve "onSuccess" functionality
    fun removeFavoriteFromDatabase(movieDetails: MovieDetails, onSuccess: () -> Unit){
        viewModelScope.launch {
            favoriteRepository.removeFavorite(movieDetails)
            onSuccess()
        }
    }

    // TODO nikhil
    fun removeFromUi(movie: MovieDetails) {
//        _favoriteMovies.update {
//            it.copy(favoriteMovies = it.favoriteMovies.filter { it.id != movie.id })
//        }
    }
}