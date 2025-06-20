package com.nikhil.movietime.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nikhil.movietime.ui.favorite.FavoriteScreen
import com.nikhil.movietime.ui.home.HomeScreen
import com.nikhil.movietime.ui.search.SearchScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { HomeScreen() }
        composable("search") { SearchScreen() }
        composable("favorite") { FavoriteScreen() }
    }
}
