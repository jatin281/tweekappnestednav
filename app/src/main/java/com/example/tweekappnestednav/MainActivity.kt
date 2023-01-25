package com.example.tweekappnestednav

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tweekappnestednav.graphs.AuthScreen
import com.example.tweekappnestednav.graphs.PlayerDetails
import com.example.tweekappnestednav.graphs.RootNavigationGraph
import com.example.tweekappnestednav.shared.BottomNavItem
import com.example.tweekappnestednav.shared.BottomNavigationBar
import com.example.tweekappnestednav.ui.theme.TweekAppNestedNavTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

                MainScreen()

        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun MainScreen() {
    TweekAppNestedNavTheme {

        val navController = rememberNavController()
        var showBottomBar by rememberSaveable { mutableStateOf(true) }
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        showBottomBar = when (navBackStackEntry?.destination?.route) {
            AuthScreen.Login.route -> false // on this screen bottom bar should be hidden
            AuthScreen.Otp.route -> false // here too
            PlayerDetails.EnterNameScreen.route -> false // here too
            PlayerDetails.DobScreen.route -> false // here too
            PlayerDetails.GenderScreen.route -> false // here too
            PlayerDetails.WeightScreen.route -> false // here too
            PlayerDetails.HeightScreen.route -> false // here too
            PlayerDetails.HandednessScreen.route -> false // here too
//            PlayerDetails.ProfilePic.route -> false // here too
            else -> true // in all other cases show bottom bar
        }

        Scaffold(modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
            bottomBar =
        {   if (showBottomBar)
            BottomNavigationBar( items = listOf(
                BottomNavItem(
                    name = "Kits",
                    route = "kits",
                    icon = Icons.Default.Star
                ),
                BottomNavItem(
                    name = "Home",
                    route = "home",
                    icon = Icons.Default.Home,
                ),
                BottomNavItem(
                    name = "Groups",
                    route = "groups",
                    icon = Icons.Default.Person,
                ),
            ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                })
        },
            content = {
                Box(modifier = Modifier.padding(it)){
                    RootNavigationGraph(navController)
                }

            }
        )


    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TweekAppNestedNavTheme {
        MainScreen()
    }
}