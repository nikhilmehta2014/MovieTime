package com.nikhil.movietime.ui.search.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.nikhil.movietime.ui.search.domain.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

}