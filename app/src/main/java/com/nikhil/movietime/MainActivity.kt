package com.nikhil.movietime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nikhil.movietime.ui.home.presentation.BottomNavigationBar
import com.nikhil.movietime.ui.home.presentation.bottomNavItems
import com.nikhil.movietime.ui.navigation.NavigationGraph
import com.nikhil.movietime.ui.theme.MovieTimeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)

        setContent {
            MovieTimeTheme {
                val navController = rememberNavController()
                MainScreen(navController = navController)
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: bottomNavItems()[0].route

    val showBottomBar = currentRoute in bottomNavItems().map { it.route }

    Scaffold(
        containerColor = Color(0xFF121212),
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(
                    selectedRoute = currentRoute,
                    items = bottomNavItems(),
                    onItemSelected = { item ->
                        if (currentRoute != item.route) {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            NavigationGraph(navController)
        }
    }
}