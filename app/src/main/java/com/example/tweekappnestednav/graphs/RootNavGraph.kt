package com.example.tweekappnestednav.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tweekappnestednav.screens.HomeScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController)
        playerDetailsNavGraph(navController = navController)
        bottomNavGraph(navController = navController)
        fabNavGraph(navController = navController)
        groupNavGraph(navController = navController)
        homeNavGraph(navController = navController)
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val PLAYER_DETAILS = "player_details_graph"
    const val BOTTOM_NAV = "bottom_nav_graph"
    const val FAB = "fab_graph"
    const val GROUP = "group_graph"
    const val HOME = "home_graph"
}