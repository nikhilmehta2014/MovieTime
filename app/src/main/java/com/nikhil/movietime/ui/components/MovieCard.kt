package com.nikhil.movietime.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.nikhil.movietime.core.domain.model.Movie

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movie: Movie? = null,
    isLoading: Boolean = false,
    onClick: (Movie) -> Unit = {}
) {
    val cardModifier = modifier
        .width(140.dp)
        .height(260.dp)
        .then(
            if (!isLoading && movie != null) Modifier.clickable { onClick(movie) }
            else Modifier
        )

    Card(
        modifier = cardModifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column {
            if (isLoading) {
                Spacer(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .background(Brush.shimmer())
                )
            } else {
                val imageUrl = "https://image.tmdb.org/t/p/w500${movie?.posterUrl}"
                Image(
                    painter = rememberAsyncImagePainter(model = imageUrl),
                    contentDescription = movie?.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (isLoading) {
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                        .fillMaxWidth()
                        .background(Brush.shimmer())
                )
            } else {
                Text(
                    text = movie?.title.orEmpty(),
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp),
                    maxLines = 2,
                    minLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
