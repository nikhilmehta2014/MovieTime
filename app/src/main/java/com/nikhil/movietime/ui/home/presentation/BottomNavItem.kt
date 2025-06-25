package com.nikhil.movietime.ui.home.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.nikhil.movietime.R
import com.nikhil.movietime.ui.navigation.Routes

@Immutable
data class BottomNavItem(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val label: String
)

@Composable
fun bottomNavItems(): List<BottomNavItem> {
    return listOf(
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
}


