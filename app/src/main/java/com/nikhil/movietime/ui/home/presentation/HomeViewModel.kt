package com.nikhil.movietime.ui.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.movietime.core.network.NetworkMonitor
import com.nikhil.movietime.ui.home.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    networkMonitor: NetworkMonitor
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    private val hasLoadedOnce = AtomicBoolean(false)

    init {
        viewModelScope.launch {
            networkMonitor.isConnected.collect { isOnline ->
                state = state.copy(isConnected = isOnline)

                if (isOnline && !hasLoadedOnce.get()) {
                    loadMovies()
                    hasLoadedOnce.set(true)
                }
            }
        }
    }

    private fun loadMovies() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val trending = homeRepository.getTrendingMovies()
                val nowPlaying = homeRepository.getNowPlayingMovies()
                state = state.copy(
                    trending = trending,
                    nowPlaying = nowPlaying,
                    isLoading = false,
                    errorMessage = null
                )
            } catch (e: Exception) {
                state = state.copy(
                    errorMessage = e.localizedMessage ?: "Unknown error",
                    isLoading = false
                )
            }
        }
    }
}