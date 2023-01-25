package com.example.tweekappnestednav.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tweekappnestednav.graphs.Home.SessionInfo.id
import com.example.tweekappnestednav.screens.GroupsScreen
import com.example.tweekappnestednav.screens.HomeScreen
import com.example.tweekappnestednav.screens.KitsScreen

@RequiresApi(Build.VERSION_CODES.O)

fun NavGraphBuilder.bottomNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.BOTTOM_NAV,
        startDestination = BottomNavigation.HomeScreen.route
    ) {
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
    navGraphBuilder.composable(route = BottomNavigation.HomeScreen.route)
    { navBackStackEntry ->

        val args = navBackStackEntry.arguments

        HomeScreen { id ->
            navController.navigate(Home.SessionInfo.withArgs(id))
        }

    }
    }

private fun addKitsScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = BottomNavigation.KitsScreen.route)
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
    navGraphBuilder.composable(route = BottomNavigation.GroupScreen.route)
    { navBackStackEntry ->

        val args = navBackStackEntry.arguments

        GroupsScreen(

            navigateToJoinOrgScreen = {
                navController.navigate(Graph.FAB)
            } ,

            navigateToCreateOrgScreen = {
                navController.navigate(Fab.CreateOrg.route)
            },

            navigateToGroupInfoScreen = {
                navController.navigate(Group.GroupInfo.route)
            }
        )

    }
}

sealed class BottomNavigation(val route: String) {

    object HomeScreen : PlayerDetails("home")

    object KitsScreen: PlayerDetails("kits")

    object GroupScreen: PlayerDetails("groups")


}