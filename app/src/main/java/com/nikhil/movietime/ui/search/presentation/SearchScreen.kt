package com.nikhil.movietime.ui.search.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nikhil.movietime.R
import com.nikhil.movietime.core.domain.model.Movie
import com.nikhil.movietime.ui.components.ErrorCard
import com.nikhil.movietime.ui.components.HeadingTitle
import com.nikhil.movietime.ui.components.MovieListItem
import com.nikhil.movietime.ui.components.shimmer
import com.nikhil.movietime.ui.navigation.Routes
import com.nikhil.movietime.ui.search.data.mapper.toMovie

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val favoriteMovieIds by viewModel.favoriteMovieIds.collectAsState()

    if (!state.isConnected) {
        ErrorCard(
            imageId = R.drawable.cloud_off,
            imageContentDescription = stringResource(R.string.desc_no_internet),
            text = stringResource(R.string.error_no_internet_connection)
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212))
        ) {
            SearchBarContainer(state, viewModel)
            if (state.errorMessage != null) {
                Text(
                    text = state.errorMessage ?: "",
                    color = Color.Red,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 24.dp, top = 4.dp)
                )
            }
            TitleRow(state)
            MoviesList(navController, state, viewModel, favoriteMovieIds)
        }
    }
}

@Composable
fun SearchBarContainer(state: SearchUiState, viewModel: SearchViewModel) {
    val searchBarGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF383838),
            Color(0xFF2C2C2C),
            Color(0xFF242424)
        )
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(56.dp),
        shape = RoundedCornerShape(30.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = searchBarGradient, shape = RoundedCornerShape(30.dp))
                .clip(RoundedCornerShape(30.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = state.query,
                    onValueChange = { viewModel.onSearchQueryChanged(it) },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.find_movies),
                            color = Color.White.copy(alpha = 0.6f)
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                )
            }
        }
    }
}

@Composable
fun TitleRow(state: SearchUiState) {
    if (state.isLoading) {
        Spacer(
            modifier = Modifier
                .padding(top = 8.dp)
                .height(16.dp)
                .fillMaxWidth(0.4f)
                .background(Brush.shimmer())
        )
    } else if (state.movies.isNotEmpty()) {
        HeadingTitle(title = stringResource(R.string.movies))
    }
}

@Composable
fun MoviesList(
    navController: NavController,
    state: SearchUiState,
    viewModel: SearchViewModel,
    favoriteMovieIds: Set<Int>
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(6) {
                    MovieListItem(
                        movie = Movie(id = 0, title = "", posterUrl = ""),
                        isLoading = true
                    )
                }
            }
        } else {
            when {
                state.movies.isNotEmpty() -> {
                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(state.movies) { movie ->
                            MovieListItem(
                                movie = movie.toMovie(),
                                onClick = {
                                    navController.navigate("${Routes.MOVIE_DETAILS}/${movie.id}")
                                },
                                onFavoriteClick = { movieDetails ->
                                    viewModel.toggleFavorite(movieDetails)
                                },
                                isFavorite = favoriteMovieIds.contains(movie.id),
                            )
                        }
                    }
                }

                state.query.isEmpty() -> {
                    ErrorCard(
                        imageId = R.drawable.movie_search,
                        imageContentDescription = stringResource(R.string.error_search_for_movies),
                        text = stringResource(R.string.error_search_for_movies),
                    )
                }

                state.query.length >= 3 -> {
                    // Show "No results found" only when search is valid but result is empty
                    ErrorCard(
                        imageId = R.drawable.no_movie_found,
                        imageContentDescription = stringResource(R.string.error_no_movie_found),
                        text = stringResource(R.string.error_no_movie_found),
                    )
                }
            }
        }
    }
}