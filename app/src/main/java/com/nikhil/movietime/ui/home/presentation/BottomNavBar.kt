package com.nikhil.movietime.ui.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationBar(
    selectedRoute: String,
    items: List<BottomNavItem>,
    onItemSelected: (BottomNavItem) -> Unit
) {
    val backgroundColor = Color(0xFF1C1F2E)
    val selectedColor = Color(0xFFFFC107)
    val unselectedColor = Color.White

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .height(80.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .width(320.dp)
                .height(56.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(backgroundColor)
                .shadow(8.dp, RoundedCornerShape(32.dp)),
            color = backgroundColor
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEach { item ->
                    val isSelected = item.route == selectedRoute

                    IconButton(onClick = { onItemSelected(item) }) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            tint = if (isSelected) selectedColor else unselectedColor,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}