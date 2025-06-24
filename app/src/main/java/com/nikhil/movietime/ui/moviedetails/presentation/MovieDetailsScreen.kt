package com.nikhil.movietime.ui.moviedetails.presentation

import android.content.Intent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.nikhil.movietime.R
import com.nikhil.movietime.ui.components.LabelCard
import com.nikhil.movietime.ui.components.ErrorCard
import com.nikhil.movietime.ui.components.MenuIconButton
import com.nikhil.movietime.ui.components.shimmer

@Composable
fun MovieDetailsScreen(
    movieId: Int,
    navController: NavController,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
) {

    LaunchedEffect(movieId) {
        viewModel.checkFavoriteStatus(movieId)
    }

    val state = viewModel.state.collectAsState().value
    val context = LocalContext.current

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Box(modifier = Modifier.fillMaxSize()) {
        // TODO - This is being called continuously, check it
        // Show offline screen if no internet + no local data
//        Log.d("asdf", "MovieDetailsScreen, state.hasLocalData=${state.hasLocalData}")
        if (!state.isConnected && !state.hasLocalData && !state.isLoading) {
            ErrorCard(
                imageId = R.drawable.cloud_off,
                imageContentDescription = "No Internet",
                text = "No Internet Connection"
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                // Top Backdrop
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight * 0.3f)
                ) {
                    if (state.isLoading) {
                        Spacer(
                            modifier = Modifier
                                .matchParentSize()
                                .background(Brush.shimmer())
                        )
                    } else {
                        AsyncImage(
                            model = state.movie?.backdropUrl?.takeIf { it.isNotEmpty() }
                                ?.let { "https://image.tmdb.org/t/p/w780$it" },
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            error = painterResource(R.drawable.error_banner),
                            fallback = painterResource(R.drawable.fallback_banner),
                            modifier = Modifier.matchParentSize()
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(top = screenHeight * 0.25f)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (state.isLoading) {
                            Spacer(
                                modifier = Modifier
                                    .height(screenHeight * 0.2f)
                                    .width(screenWidth * 0.25f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Brush.shimmer())
                            )
                        } else {
                            AsyncImage(
                                model = state.movie?.posterUrl?.takeIf { it.isNotEmpty() }
                                    ?.let { "https://image.tmdb.org/t/p/w342$it" },
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                error = painterResource(R.drawable.error_poster),
                                fallback = painterResource(R.drawable.fallback_poster),
                                modifier = Modifier
                                    .height(screenHeight * 0.2f)
                                    .width(screenWidth * 0.25f)
                                    .graphicsLayer {
                                        shadowElevation = 16f
                                        shape = RoundedCornerShape(12.dp)
                                        clip = true
                                        renderEffect = null
                                    }
                                    .background(Color.White, RoundedCornerShape(12.dp))
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(modifier = Modifier.fillMaxWidth()) {
                            if (state.isLoading) {
                                Spacer(
                                    modifier = Modifier
                                        .height(24.dp)
                                        .fillMaxWidth(0.6f)
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(Brush.shimmer())
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Spacer(
                                    modifier = Modifier
                                        .height(18.dp)
                                        .fillMaxWidth(0.4f)
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(Brush.shimmer())
                                )
                            } else {
                                Text(
                                    text = state.movie?.title.orEmpty(),
                                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                    color = Color.White
                                )
                                state.movie?.tagline?.takeIf { it.isNotEmpty() }?.let {
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.White,
                                        fontStyle = FontStyle.Italic
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        state.movie?.let {
                            val labelList = buildList {
                                it.runtime.takeIf { it.isNotEmpty() }?.let { add(it) }
                                it.releaseYear.takeIf { it.isNotEmpty() }?.let { add(it) }
                                add(it.adult)
                                addAll(it.genres)
                            }
                            items(labelList) { label ->
                                LabelCard(text = label)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    if (state.isLoading) {
                        repeat(4) {
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(16.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(Brush.shimmer())
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    } else {
                        Text(
                            text = state.movie?.overview.orEmpty(),
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }

        // Back, Share and Bookmark buttons
        MenuIconButton(
            alignment = Alignment.TopStart,
            startPadding = 12.dp,
            topPadding = 12.dp,
            onClick = { navController.popBackStack() },
            image = Icons.Default.ArrowBack,
            contentDescription = "Back"
        )

        if (state.movie != null) {
            MenuIconButton(
                alignment = Alignment.TopEnd,
                topPadding = 12.dp,
                endPadding = 12.dp,
                onClick = {
                    state.movie.let { movie ->
                        val shareUri = "https://movietime.fake/movie/$movieId"
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_SUBJECT, "Check out this movie: ${movie.title}")
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "Watch this movie on MovieTime:\n$shareUri"
                            )
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "Share via"))
                    }
                },
                image = Icons.Default.Share,
                contentDescription = "Share"
            )
            var isFavorite = false
            MenuIconButton(
                alignment = Alignment.TopEnd,
                topPadding = 68.dp, // 12dp top + 40dp height + 16dp space
                endPadding = 12.dp,
                onClick = {
                    state.movie.let { viewModel.toggleFavorite(it) }
                },
                image = if (viewModel.isFavorite.value)
                {
                    isFavorite = true
                    Icons.Default.Favorite
                }
                else
                {
                    isFavorite = false
                    Icons.Default.FavoriteBorder
                },
                contentDescription = "Bookmark",
                isFavorite = isFavorite
            )
        }

        if (state.movie != null) {
            state.error?.let {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = it, color = Color.Red)
                }
            }
        }
    }
}
