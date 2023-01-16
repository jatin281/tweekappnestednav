package com.example.tweekappnestednav.screens

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tweekappnestednav.BottomNavItem
import com.example.tweekappnestednav.floating.ExpandableFAB
import com.example.tweekappnestednav.graphs.HomeNavGraph
import com.example.tweekappnestednav.ui.theme.TweekAppNestedNavTheme

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()
//    popBackStack: () -> Unit,
//    popUpToLogin: () -> Unit,
) {
//    val navController = rememberNavController()

//    Scaffold(
//        bottomBar = { BottomNavigationBar(navController = navController, items = listOf(),onItemClick = {
//                    navController.navigate(it.route)
//                })}
//    ) {
//        HomeNavGraph(navController = navController)
//    }



    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
            )
        },
//        modifier = Modifier.padding( bottom = 20.dp)
    ) {paddingValues ->

        Column(
            modifier = Modifier
                .padding(
                    bottom = paddingValues.calculateBottomPadding()
                )
                .background(Color.White),
        ){
            HomeNavGraph(navController = navController)
        }

    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(navController = navController, startDestination = "groups") {
        composable("kits") {
            KitsScreen()
        }
        composable("sessions") {
            HomeScreen(navigateToJoinOrgScreen= {})
        }
        composable("groups") {
            GroupsScreen(navigateToJoinOrgScreen= {})
        }


    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomNavItem.Kits,
        BottomNavItem.Home,
        BottomNavItem.Groups,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation(modifier = Modifier,
        backgroundColor = Color.White,
        elevation = 5.dp) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}


//@Composable
//fun BottomNavigationBar(
//    items: List<BottomNavItem>,
//    navController: NavHostController,
//    modifier: Modifier = Modifier,
//    onItemClick: (BottomNavItem) -> Unit
//) {
//    val backStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = backStackEntry?.destination
//
//    val selected = items.any { it.route == currentDestination?.route }
//    BottomNavigation(
//        modifier = modifier,
//        backgroundColor = Color.DarkGray,
//        elevation = 5.dp
//    ) {
////        items.forEach { item ->
////            val selected = item.route == backStackEntry?.destination?.route
//
//            if (selected) {
//                BottomNavigation {
//                    items.forEach { item ->
//                        AddItem(
//                            screen = item,
//                            currentDestination = currentDestination,
//                            navController = navController
//                        )
//                    }
//                }
//            }
//
////            BottomNavigationItem(
////                selected = selected,
////                onClick = { onItemClick(item) },
////                selectedContentColor = Orange ,
////                unselectedContentColor = Color.Gray,
////                icon = {
////                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
////                        if(item.badgeCount > 0) {
////                            BadgedBox(
////                                badge = {
////                                    Text(text = item.badgeCount.toString())
////                                }
////                            ) {
////                                Icon(
////                                    imageVector = item.icon,
////                                    contentDescription = item.name
////                                )
////                            }
////                        } else {
////                            Icon(
////                                imageVector = item.icon,
////                                contentDescription = item.name
////                            )
////                        }
////                        if(selected) {
////                            Text(
////                                text = item.name,
////                                textAlign = TextAlign.Center,
////                                fontSize = 10.sp
////                            )
////                        }
////                    }
////                }
////            )
////        }
//    }
//}

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.name)
        },
        icon = {
            androidx.compose.material.Icon(

                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KitsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Kits screen")
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    navigateToJoinOrgScreen: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Home screen", fontSize = 20.sp, color = Color.Blue)

//        Button(onClick = navigateToJoinOrgScreen, modifier = Modifier.fillMaxWidth().padding(30.dp)){
//            Text(text = "Get Started")
//        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GroupsScreen(
    navigateToJoinOrgScreen: () -> Unit,
) {
    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Tweek Home", fontSize = 30.sp, color = Color.Blue, modifier = Modifier.align(
            Alignment.TopStart
        ))
        val context = LocalContext.current
        ExpandableFAB(modifier = Modifier
            .padding(bottom = 30.dp, end = 30.dp)
            .align(Alignment.BottomEnd),
            joinOrganization = navigateToJoinOrgScreen

//                Toast.makeText(
//                    context, "Join",
//                    Toast.LENGTH_SHORT
//                ).show()
            ,
            createOrganization = {
                Toast.makeText(
                    context, "Create",
                    Toast.LENGTH_SHORT
                ).show()
            })
    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    TweekAppNestedNavTheme(useSystemUiController = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            GroupsScreen(
                navigateToJoinOrgScreen = {},
            )
        }
    }
}