package com.nikhil.movietime.ui.favorite.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nikhil.movietime.R
import com.nikhil.movietime.ui.components.ErrorCard
import com.nikhil.movietime.ui.components.HeadingTitle
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
        HeadingTitle(title = stringResource(R.string.favorite_movies))
        if (favoriteMovies.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ErrorCard(
                    imageId = R.drawable.no_fav_movie,
                    imageContentDescription = stringResource(R.string.error_no_movie_marked_as_favorite),
                    text = stringResource(R.string.error_no_movie_marked_as_favorite),
                )
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
                            viewModel.removeFavoriteFromDatabase(movieDetails)
                        },
                        isFavorite = true
                    )
                }
            }
        }
    }
}