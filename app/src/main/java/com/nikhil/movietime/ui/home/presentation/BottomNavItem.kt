package com.nikhil.movietime.ui.home.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
import com.nikhil.movietime.ui.navigation.Routes

@Immutable
data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
)

val bottomNavItems = listOf(
    BottomNavItem(
        route = Routes.HOME,
        icon = Icons.Default.Home,
        label = "Home"
    ),
    BottomNavItem(
        route = Routes.SEARCH,
        icon = Icons.Default.Search,
        label = "Search"
    ),
    BottomNavItem(
        route = Routes.FAVORITE,
        icon = Icons.Default.Favorite,
        label = "Favorite"
    )
)

