package com.nikhil.movietime.ui.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nikhil.movietime.R
import com.nikhil.movietime.core.domain.model.Movie
import com.nikhil.movietime.ui.components.ErrorCard
import com.nikhil.movietime.ui.components.HeadingTitle
import com.nikhil.movietime.ui.components.MovieCard

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onMovieClick: (Int) -> Unit
) {
    val state = viewModel.state

    if (!state.isConnected && !state.hasLocalData) {
        ErrorCard(
            imageId = R.drawable.cloud_off,
            imageContentDescription = "No Internet",
            text = "No Internet Connection"
        )
        return
    }

    val renderRow: @Composable (String, List<Movie>) -> Unit = { title, movies ->
        HeadingTitle(title = title)
        MovieRow(movieList = movies, isLoading = state.isLoading, onClick = onMovieClick)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        renderRow("Trending", state.trending)
        renderRow("Now Playing", state.nowPlaying)
    }
}

@Composable
fun MovieRow(movieList: List<Movie>, isLoading: Boolean, onClick: (Int) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        if (isLoading) {
            items(5) {
                MovieCard(isLoading = true)
            }
        } else {
            items(movieList) { movie ->
                MovieCard(
                    movie = Movie(
                        id = movie.id,
                        title = movie.title,
                        posterUrl = movie.posterUrl
                    ),
                    onClick = { onClick(movie.id) }
                )
            }
        }
    }
}