package com.nikhil.movietime.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MenuIconButton(
    alignment: Alignment,
    startPadding: Dp = 0.dp,
    topPadding: Dp = 0.dp,
    endPadding: Dp = 0.dp,
    onClick: () -> Unit,
    image: ImageVector,
    contentDescription: String
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .align(alignment)
                .padding(
                    start = startPadding,
                    top = topPadding,
                    end = endPadding
                )
                .size(40.dp)
                .background(Color.Black.copy(alpha = 0.6f), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = { onClick() }) {
                Icon(
                    imageVector = image,
                    contentDescription = contentDescription,
                    tint = Color.White
                )
            }
        }
    }
}