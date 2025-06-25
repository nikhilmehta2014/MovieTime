package com.nikhil.movietime.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nikhil.movietime.R

@Composable
fun LabelCard(
    text: String,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 16.dp,
    contentColor: Color = Color.White,
    padding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
) {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF2C2C2C),
            Color(0xFF1F1F1F),
            Color(0xFF3A3A3A)
        )
    )
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(brush = gradient)
            .padding(padding)
    ) {
        Text(
            text = text,
            color = contentColor
        )
    }
}

@Preview(
    name = "LabelCard Preview",
    showBackground = true,
    backgroundColor = 0xFFEEEEEE,
    widthDp = 200,
    heightDp = 100
)
@Composable
fun LabelCardPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        LabelCard(text = stringResource(R.string.now_playing))
        Spacer(modifier = Modifier.height(8.dp))
        LabelCard(text = stringResource(R.string.trending), cornerRadius = 16.dp)
    }
}