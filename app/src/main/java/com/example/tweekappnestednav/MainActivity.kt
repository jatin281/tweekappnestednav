package com.example.tweekappnestednav

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.tweekappnestednav.graphs.RootNavigationGraph
import com.example.tweekappnestednav.ui.theme.TweekAppNestedNavTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun MainScreen() {
    TweekAppNestedNavTheme {
        val navController = rememberNavController()
        RootNavigationGraph(navController)
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