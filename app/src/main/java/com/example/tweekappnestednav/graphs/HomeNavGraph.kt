package com.example.tweekappnestednav.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.tweekappnestednav.screens.AnalyticsBowlingScreen
import com.example.tweekappnestednav.screens.GroupInfoScreen
import com.example.tweekappnestednav.screens.RequestsScreen
import com.example.tweekappnestednav.screens.SessionInfoScreen
import com.example.tweekappnestednav.screens.SessionSummaryScreen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.HOME,
        startDestination = Home.SessionInfo.route
    ) {

        addSessionInfoScreen(navController, this)
        addSessionSummaryScreen(navController, this)
        addAnalyticsBowlingScreen(navController, this)

    }
}


@RequiresApi(Build.VERSION_CODES.O)
private fun addSessionInfoScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(
        route = Home.SessionInfo.withArgsFormat(Home.SessionInfo.id),
        arguments = listOf(navArgument(Home.SessionInfo.id){
            type = NavType.IntType
            defaultValue = 0
        }) )
    { navBackStackEntry ->

        val args = navBackStackEntry.arguments

        SessionInfoScreen(
            id = args?.getInt(Home.SessionInfo.id)!!,
            navigateToSessionSummaryScreen = {
                navController.navigate(Home.SessionSummary.route)
            },
            navigateToAnalyticsBowlingScreen = {
                navController.navigate(Home.AnalyticsBowling.route)
            }
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
private fun addSessionSummaryScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = Home.SessionSummary.route)
    { navBackStackEntry ->

        val args = navBackStackEntry.arguments

        SessionSummaryScreen(

        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun addAnalyticsBowlingScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = Home.AnalyticsBowling.route)
    { navBackStackEntry ->

        val args = navBackStackEntry.arguments

        AnalyticsBowlingScreen(

        )
    }
}


sealed class Home(val route: String) {

    object SessionInfo : Home(route = "session_info"){
         val id = "id"
    }

    object SessionSummary : Home(route = "session_summary")

    object AnalyticsBowling : Home(route = "analytics_bowling")




    // build navigation path (for screen navigation)
    fun withArgs(vararg args: Any?): String {
        return buildString {
            append(route)
            args.forEach{ arg ->
                append("/$arg")
            }
        }
    }

    // build and setup route format (in navigation graph)
    fun withArgsFormat(vararg args: Any?) : String {
        return buildString {
            append(route)
            args.forEach{ arg ->
                append("/{$arg}")
            }
        }
    }
}

