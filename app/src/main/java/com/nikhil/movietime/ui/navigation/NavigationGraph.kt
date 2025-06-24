package com.nikhil.movietime.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.nikhil.movietime.ui.favorite.presentation.FavoriteScreen
import com.nikhil.movietime.ui.home.presentation.HomeScreen
import com.nikhil.movietime.ui.moviedetails.presentation.MovieDetailsScreen
import com.nikhil.movietime.ui.search.presentation.SearchScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                onMovieClick = { movieId ->
                    navController.navigate("${Routes.MOVIE_DETAILS}/$movieId")
                }
            )
        }
        composable(
            route = Routes.MOVIE_DETAILS_WITH_ARG,
            arguments = listOf(navArgument("movieId") { type = NavType.IntType }),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://movietime.fake/movie/{movieId}"
                }
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: return@composable
            MovieDetailsScreen(movieId = movieId, navController = navController)
        }
        composable(route = Routes.SEARCH) {
            SearchScreen(navController = navController)
        }
        composable("favorite") {
            FavoriteScreen(
                onMovieClick = { movieId ->
                    navController.navigate("${Routes.MOVIE_DETAILS}/${movieId}")
                }
            )
        }
    }
}
