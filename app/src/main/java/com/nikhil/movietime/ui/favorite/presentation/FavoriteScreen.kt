package com.nikhil.movietime.ui.favorite.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nikhil.movietime.ui.components.MovieListItem

@Composable
fun FavoriteScreen(
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = hiltViewModel(),
) {
    val favoriteMovies = viewModel.favoriteMovies.collectAsState().value

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Text(
            text = "Favorite Movies",
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.Start)
        )

        if (favoriteMovies.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No favorite movies found.")
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(favoriteMovies) { movie ->
                    MovieListItem(
                        movie = movie,
                        onClick = { onMovieClick(movie.id) },
                        onFavoriteClick = { movieDetails ->
                            viewModel.removeFavoriteFromDatabase(movieDetails) {
                                viewModel.removeFromUi(movieDetails)
                            }
                        },
                        isFavorite = true
                    )
                }
            }
        }
    }
}