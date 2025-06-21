package com.nikhil.movietime.ui.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.movietime.ui.home.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val trending = homeRepository.getTrendingMovies()
                val nowPlaying = homeRepository.getNowPlayingMovies()
                state = state.copy(trending = trending, nowPlaying = nowPlaying, isLoading = false)
            } catch (e: Exception) {
                state = state.copy(
                    errorMessage = e.localizedMessage ?: "Unknown error",
                    isLoading = false
                )
            }
        }
    }
}