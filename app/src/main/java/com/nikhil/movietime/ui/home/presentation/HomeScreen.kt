package com.nikhil.movietime.ui.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nikhil.movietime.R
import com.nikhil.movietime.core.domain.model.Movie
import com.nikhil.movietime.ui.components.MovieCard
import com.nikhil.movietime.ui.components.MovieTitle

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onMovieClick: (Int) -> Unit
) {
    val state = viewModel.state

    if (!state.isConnected && !state.hasLocalData && !state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.cloud_off),
                    contentDescription = "No Internet",
                    modifier = Modifier.size(200.dp)
                )
                Text(
                    text = "No Internet Connection",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        MovieTitle(text = "Trending")

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            if (state.isLoading) {
                items(5) {
                    MovieCard(isLoading = true)
                }
            } else {
                items(state.trending) { trendingMovie ->
                    MovieCard(
                        movie = Movie(
                            id = trendingMovie.id,
                            title = trendingMovie.title,
                            posterUrl = trendingMovie.posterUrl
                        ),
                        onClick = { onMovieClick(trendingMovie.id) }
                    )
                }
            }
        }

        MovieTitle(text = "Now Playing")

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            if (state.isLoading) {
                items(5) {
                    MovieCard(isLoading = true)
                }
            } else {
                items(state.nowPlaying) { nowPlayingMovie ->
                    MovieCard(
                        movie = Movie(
                            id = nowPlayingMovie.id,
                            title = nowPlayingMovie.title,
                            posterUrl = nowPlayingMovie.posterUrl
                        ),
                        onClick = { onMovieClick(nowPlayingMovie.id) }
                    )
                }
            }
        }
    }
}