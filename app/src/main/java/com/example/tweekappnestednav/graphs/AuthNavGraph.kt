package com.example.tweekappnestednav.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tweekappnestednav.BottomNavItem
import com.example.tweekappnestednav.navigation.NavRoute
import com.example.tweekappnestednav.screens.LoginScreen
import com.example.tweekappnestednav.screens.OtpScreen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        addLoginScreen(navController, this)
        addOtpScreen(navController, this)
//        playerDetailsNavGraph(navController = navController)
    }
}

private fun addLoginScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = AuthScreen.Login.route) {
        LoginScreen(
            navigateToOtp = {
                navController.navigate(AuthScreen.Otp.route)
            }
        )
    }
}

private fun addOtpScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = AuthScreen.Otp.route) {

        OtpScreen(
            navigateToEnterNameScreen = {
                navController.navigate(Graph.PLAYER_DETAILS)
            },
            popBackStack = { navController.popBackStack() },
            popUpToLogin= { popUpToLogin(navController) },
        )
    }
}

fun popUpToLogin(navController: NavHostController) {
    navController.popBackStack(AuthScreen.Login.route, inclusive = false)
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object Otp : AuthScreen(route = "OTP")
}