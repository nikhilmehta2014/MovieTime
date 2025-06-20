package com.nikhil.movietime.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector

@Immutable
data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
)

val bottomNavItems = listOf(
    BottomNavItem(
        route = "home",
        icon = Icons.Default.Home,
        label = "Home"
    ),
    BottomNavItem(
        route = "search",
        icon = Icons.Default.Search,
        label = "Search"
    ),
    BottomNavItem(
        route = "favorite",
        icon = Icons.Default.Favorite,
        label = "Favorite"
    )
)

