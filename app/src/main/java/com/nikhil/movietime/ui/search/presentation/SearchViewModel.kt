package com.nikhil.movietime.ui.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.movietime.R
import com.nikhil.movietime.core.data.repository.FavoriteRepository
import com.nikhil.movietime.core.domain.model.Movie
import com.nikhil.movietime.core.network.NetworkMonitor
import com.nikhil.movietime.core.util.StringProvider
import com.nikhil.movietime.ui.search.domain.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
    private val repository: SearchRepository,
    networkMonitor: NetworkMonitor,
    private val stringProvider: StringProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState

    private val _favoriteMovieIds = MutableStateFlow<Set<Int>>(emptySet())
    val favoriteMovieIds: StateFlow<Set<Int>> = _favoriteMovieIds.asStateFlow()

    private val queryFlow = MutableStateFlow("")

    init {
        viewModelScope.launch {
            networkMonitor.isConnected.collect { isOnline ->
                _uiState.update { it.copy(isConnected = isOnline) }
            }
        }
        observeQuery()
        getFavoriteMovieIds()
    }

    fun onSearchQueryChanged(newQuery: String) {
        _uiState.update { it.copy(query = newQuery, errorMessage = null) }
        queryFlow.value = newQuery
    }

    private fun observeQuery() {
        viewModelScope.launch {
            queryFlow
                .debounce(500)
                .distinctUntilChanged()
                .collectLatest { query ->
                    if (query.length < 3) {
                        _uiState.update {
                            it.copy(
                                movies = emptyList(),
                                isLoading = false,
                                errorMessage =
                                    if (query.isNotBlank())
                                        stringProvider.getString(R.string.error_type_3_characters)
                                    else
                                        null
                            )
                        }
                        return@collectLatest
                    }

                    _uiState.update { it.copy(isLoading = true, errorMessage = null) }

                    try {
                        val result = repository.getMovies(query)
                        _uiState.update {
                            it.copy(movies = result, isLoading = false)
                        }
                    } catch (e: Exception) {
                        _uiState.update {
                            it.copy(
                                movies = emptyList(),
                                isLoading = false,
                                errorMessage = e.message
                                    ?: stringProvider.getString(R.string.error_something_went_wrong)
                            )
                        }
                    }
                }
        }
    }

    private fun getFavoriteMovieIds() {
        viewModelScope.launch {
            favoriteRepository.getFavoriteMovieIds()
                .collect { ids ->
                    _favoriteMovieIds.value = ids
                }
        }
    }

    fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            val currentFavorites = favoriteMovieIds.value
            if (movie.id in currentFavorites) {
                favoriteRepository.removeFavorite(movie)
            } else {
                favoriteRepository.saveFavorite(movie)
            }
        }
    }
}