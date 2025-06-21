package com.nikhil.movietime.ui.moviedetails.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.rememberAsyncImagePainter

@Composable
fun MovieDetailsScreen(
    movieId: Int,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    state.movie?.let { movie ->
        val backdropUrl = "https://image.tmdb.org/t/p/w780${movie.backdropUrl}"
        val posterUrl = "https://image.tmdb.org/t/p/w342${movie.posterUrl}"

        Box(
            modifier = Modifier
                .fillMaxSize()
//                .background(Color(0xFF448AFF))
//                .background(Color(0xFF4DB6AC))
                .background(Color(0xFF8D6E63))
                .verticalScroll(rememberScrollState())
        ) {
            // Backdrop with bottom 10% blur simulation
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight * 0.3f)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(backdropUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )

                // Simulated blur (fade) at bottom 10%
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .height(screenHeight * 0.03f) // 10% of 30% of screen height
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.7f),
                                    Color.Transparent
                                ),
                                startY = Float.POSITIVE_INFINITY,
                                endY = 0f
                            )
                        )
                )
            }

            Column(
                modifier = Modifier
                    .padding(top = screenHeight * 0.25f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Poster image
                    Image(
                        painter = rememberAsyncImagePainter(posterUrl),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(screenHeight * 0.2f)
                            .width(screenWidth * 0.25f)
                            .clip(RoundedCornerShape(8.dp))
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    // Title and Tagline
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = movie.title,
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = Color.White,
                        )
                        if (movie.tagline.isNotEmpty()) {
                            Text(
                                text = movie.tagline,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White,
                                fontStyle = FontStyle.Italic
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Overview section
                Text(
                    text = movie.overview,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }

    state.error?.let {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = it, color = Color.Red)
        }
    }
}