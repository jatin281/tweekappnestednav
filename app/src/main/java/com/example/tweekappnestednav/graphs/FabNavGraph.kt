package com.example.tweekappnestednav.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tweekappnestednav.screens.JoinOrgScreen

fun NavGraphBuilder.fabNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.FAB,
        startDestination = Fab.JoinOrg.route
    ) {
        composable(route = Fab.JoinOrg.route) {
            addJoinOrgScreen(navController, this)
        }
        composable(route = Fab.CreateOrg.route) {

        }
    }
}

private fun addJoinOrgScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = Fab.JoinOrg.route)
    { navBackStackEntry ->

        val args = navBackStackEntry.arguments

        JoinOrgScreen(
//            navigateToProfilePicScreen = {
//                navController.navigate(NavRoute.ProfilePic.path)
//            },

        )
    }
}

sealed class Fab(val route: String) {
    object JoinOrg : Fab(route = "join_org")
    object CreateOrg : Fab(route = "create_org")
}