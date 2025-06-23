package com.nikhil.movietime.ui.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.movietime.core.domain.model.Movie
import com.nikhil.movietime.core.domain.repository.MovieRepository
import com.nikhil.movietime.core.network.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    networkMonitor: NetworkMonitor
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    private val hasSyncedOnce = AtomicBoolean(false)

    init {
        viewModelScope.launch {
            networkMonitor.isConnected.collect { isOnline ->
                state = state.copy(isConnected = isOnline)

                if (isOnline && !hasSyncedOnce.get()) {
                    movieRepository.refreshHomeMovies()
                    hasSyncedOnce.set(true)
                }
            }
        }

        viewModelScope.launch {
            state = state.copy(isLoading = true)
            combine(
                movieRepository.getTrendingMovies(),
                movieRepository.getNowPlayingMovies()
            ) { trending, nowPlaying ->
                trending to nowPlaying
            }.catch { e ->
                state = state.copy(
                    trending = emptyList(),
                    nowPlaying = emptyList(),
                    isLoading = false,
                    errorMessage = e.localizedMessage ?: "Something went wrong"
                )
            }.collect { (trending, nowPlaying) ->
                val hasLocal = trending.isNotEmpty() || nowPlaying.isNotEmpty()
                if (hasLocal){
                    state = state.copy(
                        trending = trending,
                        nowPlaying = nowPlaying,
                        isLoading = false,
                        errorMessage = null,
                        hasLocalData = hasLocal
                    )
                }
            }
        }
    }
}