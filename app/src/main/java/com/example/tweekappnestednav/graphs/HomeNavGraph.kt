package com.example.tweekappnestednav.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tweekappnestednav.BottomNavItem
import com.example.tweekappnestednav.navigation.NavRoute
import com.example.tweekappnestednav.screens.GroupsScreen
import com.example.tweekappnestednav.screens.HomeScreen
import com.example.tweekappnestednav.screens.KitsScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomNavItem.Home.route
    )  {
            addHomeScreen(navController, this)

            addKitsScreen(navController, this)

            addGroupsScreen(navController, this)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun addHomeScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = BottomNavItem.Home.route)
    { navBackStackEntry ->

        val args = navBackStackEntry.arguments

        HomeScreen(
//            navigateToJoinOrgScreen = {
//                navController.navigate(NavRoute.JoinOrgScreen.path)
//            },
//            popBackStack = { navController.popBackStack() },
//            popUpToLogin = { com.example.tweekappnestednav.navigation.popUpToLogin(navController) }
        )
    }
}

private fun addKitsScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = BottomNavItem.Kits.route)
    {

        KitsScreen(

        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun addGroupsScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = BottomNavItem.Groups.route)
    { navBackStackEntry ->

        val args = navBackStackEntry.arguments

        GroupsScreen(
            navigateToJoinOrgScreen = {
                navController.navigate(Fab.JoinOrg.route)
            }
        )

    }
}