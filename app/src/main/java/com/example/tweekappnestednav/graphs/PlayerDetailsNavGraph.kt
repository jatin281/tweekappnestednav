package com.example.tweekappnestednav.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tweekappnestednav.BottomNavItem
import com.example.tweekappnestednav.screens.DobScreen
import com.example.tweekappnestednav.screens.EnterNameScreen
import com.example.tweekappnestednav.screens.GenderScreen
import com.example.tweekappnestednav.screens.HandednessScreen
import com.example.tweekappnestednav.screens.HeightScreen
import com.example.tweekappnestednav.screens.HomeScreen
import com.example.tweekappnestednav.screens.ProfilePicCoachScreen
import com.example.tweekappnestednav.screens.WeightScreen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.playerDetailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.PLAYER_DETAILS,
        startDestination = PlayerDetails.EnterNameScreen.route
    ) {

            addEnterNameScreen(navController, this)

            addDobScreen(navController, this)

            addGenderScreen(navController, this)

            addWeightScreen(navController, this)

            addHeightScreen(navController, this)

            addHandednessScreen(navController, this)

            addProfilePicScreen(navController, this)

    }
}

private fun addEnterNameScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = PlayerDetails.EnterNameScreen.route)
    { navBackStackEntry ->

        val args = navBackStackEntry.arguments

        EnterNameScreen(
            navigateToDobScreen = {
                navController.navigate(PlayerDetails.DobScreen.route)
            },
            popBackStack = { navController.popBackStack() },

        )
    }
}

private fun addDobScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = PlayerDetails.DobScreen.route)
    { navBackStackEntry ->

        val args = navBackStackEntry.arguments

        DobScreen(
            navigateToGenderScreen = {
                navController.navigate(PlayerDetails.GenderScreen.route)
            },
            popBackStack = { navController.popBackStack() },
            popUpToLogin = { com.example.tweekappnestednav.graphs.popUpToLogin(navController) }
        )
    }
}

private fun addGenderScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = PlayerDetails.GenderScreen.route)
    { navBackStackEntry ->

        val args = navBackStackEntry.arguments

        GenderScreen(
            navigateToWeightScreen = {
                navController.navigate(PlayerDetails.WeightScreen.route)
            },
            popBackStack = { navController.popBackStack() },
            popUpToLogin = { com.example.tweekappnestednav.graphs.popUpToLogin(navController) }
        )
    }
}

private fun addWeightScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = PlayerDetails.WeightScreen.route)
    { navBackStackEntry ->

        val args = navBackStackEntry.arguments

        WeightScreen(
            navigateToHeightScreen = {
                navController.navigate(PlayerDetails.HeightScreen.route)
            },
            popBackStack = { navController.popBackStack() },
            popUpToLogin = { com.example.tweekappnestednav.graphs.popUpToLogin(navController) }
        )
    }
}

private fun addHeightScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = PlayerDetails.HeightScreen.route)
    { navBackStackEntry ->

        val args = navBackStackEntry.arguments

        HeightScreen(
            navigateToHandednessScreen = {
                navController.navigate(PlayerDetails.HandednessScreen.route)
            },
            popBackStack = { navController.popBackStack() },
            popUpToLogin = { com.example.tweekappnestednav.graphs.popUpToLogin(navController) }
        )
    }
}

private fun addHandednessScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = PlayerDetails.HandednessScreen.route)
    { navBackStackEntry ->

        val args = navBackStackEntry.arguments

        HandednessScreen(
            navigateToProfilePicScreen = {
                navController.navigate(PlayerDetails.ProfilePic.route)
            },
            popBackStack = { navController.popBackStack() },
            popUpToLogin = { com.example.tweekappnestednav.graphs.popUpToLogin(navController) }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun addProfilePicScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = PlayerDetails.ProfilePic.route)
    { navBackStackEntry ->

        val args = navBackStackEntry.arguments

        ProfilePicCoachScreen(
            navigateToHomeScreen = {
                navController.navigate(Graph.HOME)
            },
            popBackStack = { navController.popBackStack() },
            popUpToLogin = { com.example.tweekappnestednav.graphs.popUpToLogin(navController) }
        )
    }
}



sealed class PlayerDetails(val route: String) {

    object EnterNameScreen : PlayerDetails("enter_name")

    object DobScreen: PlayerDetails("dob")

    object GenderScreen: PlayerDetails("gender_screen")

    object WeightScreen: PlayerDetails("weight_screen")

    object HeightScreen: PlayerDetails("height_screen")

    object HandednessScreen: PlayerDetails("handedness_screen")

    object ProfilePic: PlayerDetails("profile_pic")
}

