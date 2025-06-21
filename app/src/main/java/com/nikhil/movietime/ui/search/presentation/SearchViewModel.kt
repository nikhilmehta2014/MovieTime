package com.nikhil.movietime.ui.search.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.movietime.ui.search.domain.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState

    private val queryFlow = MutableStateFlow("")

    init {
        observeQuery()
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
                                errorMessage = if (query.isNotBlank()) "Type at least 3 characters" else null
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
                                errorMessage = e.message ?: "Something went wrong"
                            )
                        }
                    }
                }
        }
    }
}