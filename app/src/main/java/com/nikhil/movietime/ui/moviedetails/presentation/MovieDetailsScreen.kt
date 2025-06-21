package com.nikhil.movietime.ui.moviedetails.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MovieDetailsScreen(movieId: Int) {
    Text(
        text = "Movie Details for ID: $movieId",
        color = Color.White,
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.padding(24.dp)
    )

    // TODO: Use movieId in ViewModel to fetch details
}