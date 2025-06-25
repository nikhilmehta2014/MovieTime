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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikhil.movietime.R
import com.nikhil.movietime.ui.navigation.Routes

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
                            imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
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

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun BottomNavigationBarPreview() {
    val items = listOf(
        BottomNavItem(
            route = Routes.HOME,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            label = stringResource(R.string.home)
        ),
        BottomNavItem(
            route = Routes.SEARCH,
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search,
            label = stringResource(R.string.search)
        ),
        BottomNavItem(
            route = Routes.FAVORITE,
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder,
            label = stringResource(R.string.favorite)
        )
    )

    var selectedRoute by remember { mutableStateOf(Routes.HOME) }

    BottomNavigationBar(
        selectedRoute = selectedRoute,
        items = items,
        onItemSelected = { selectedRoute = it.route }
    )
}