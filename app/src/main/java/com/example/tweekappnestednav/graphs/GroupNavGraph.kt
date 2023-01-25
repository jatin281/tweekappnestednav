package com.example.tweekappnestednav.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tweekappnestednav.screens.CreateOrgScreen
import com.example.tweekappnestednav.screens.GroupInfoScreen
import com.example.tweekappnestednav.screens.JoinOrgScreen
import com.example.tweekappnestednav.screens.RequestsScreen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.groupNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.GROUP,
        startDestination = Group.GroupInfo.route
    ) {

        addGroupInfoScreen(navController, this)
        addRequestsScreen(navController, this)

    }
}


@RequiresApi(Build.VERSION_CODES.O)
private fun addGroupInfoScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = Group.GroupInfo.route)
    { navBackStackEntry ->

        val args = navBackStackEntry.arguments

        GroupInfoScreen(
            navigateToRequestsScreen = {
                navController.navigate(Group.Request.route)
            },
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun addRequestsScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = Group.Request.route)
    { navBackStackEntry ->

        val args = navBackStackEntry.arguments

        RequestsScreen(

        )
    }
}

sealed class Group(val route: String) {
    object GroupInfo : Fab(route = "group_info")
    object Request : Fab(route = "request")
}