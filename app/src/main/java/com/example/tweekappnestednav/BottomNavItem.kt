package com.example.tweekappnestednav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
    val badgeCount: Int = 0
){
    object Kits : BottomNavItem(
        route = "kits",
        name = "Kits",
        icon = Icons.Outlined.Star
    )

    object Home : BottomNavItem(
        name = "Home",
        route = "home",
        icon = Icons.Default.Home,
    )

    object Groups : BottomNavItem(
        name = "Groups",
        route = "groups",
        icon = Icons.Default.Person,
    )
}
