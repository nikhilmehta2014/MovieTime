package com.nikhil.movietime.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.nikhil.movietime.R
import com.nikhil.movietime.core.domain.model.Movie

@Composable
fun MovieListItem(
    movie: Movie,
    onClick: () -> Unit = {},
    onFavoriteClick: (Movie) -> Unit = {},
    isFavorite: Boolean = false,
    isLoading: Boolean = false
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .then(if (!isLoading) Modifier.clickable { onClick() } else Modifier),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isLoading) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(2f / 3f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Brush.shimmer())
                    )
                } else {
                    AsyncImage(
                        model = movie.posterUrl.takeIf { it.isNotEmpty() }
                            ?.let { "https://image.tmdb.org/t/p/w185${movie.posterUrl}" },
                        contentDescription = movie.title,
                        contentScale = ContentScale.Crop,
                        error = painterResource(R.drawable.error_poster),
                        fallback = painterResource(R.drawable.fallback_poster),
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(2f / 3f)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Top
                ) {
                    if (isLoading) {
                        Spacer(
                            modifier = Modifier
                                .height(24.dp)
                                .fillMaxWidth(0.7f)
                                .clip(RoundedCornerShape(4.dp))
                                .background(Brush.shimmer())
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Spacer(
                            modifier = Modifier
                                .height(16.dp)
                                .fillMaxWidth(0.4f)
                                .clip(RoundedCornerShape(4.dp))
                                .background(Brush.shimmer())
                        )
                    } else {
                        Text(
                            text = movie.title,
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(top = 8.dp, end = 48.dp)
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        val hasYear = movie.releaseYear.isNotBlank()
                        val hasVotes = movie.rating > 0

                        if (hasYear || hasVotes) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                if (hasYear) {
                                    Text(
                                        text = movie.releaseYear,
                                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                        color = Color.Gray
                                    )
                                }

                                if (hasYear && hasVotes) {
                                    Text(
                                        text = " \u2022 ",
                                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                        color = Color.Gray
                                    )
                                }

                                if (hasVotes) {
                                    Text(
                                        text = "${movie.rating}",
                                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                        color = Color.Gray
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        if (!isLoading) {
            IconButton(
                onClick = { onFavoriteClick(movie) },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = if (isFavorite) "Unfavorite" else "Favorite",
                    tint = if (isFavorite) Color.Red else Color.Gray
                )
            }
        }
    }
}