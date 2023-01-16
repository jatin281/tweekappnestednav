package com.example.tweekappnestednav.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.composable
import com.example.tweekappnestednav.R
import com.example.tweekappnestednav.graphs.Graph

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePicCoachScreen(
    navigateToHomeScreen: () -> Unit,
    popBackStack: () -> Unit,
    popUpToLogin: () -> Unit,
) {
    var textFieldState by remember{
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = "Profile Picture", fontSize = 30.sp, color = Color.Blue)

        Column(modifier = Modifier
            .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(painter = painterResource(id = R.drawable.profile_male), contentDescription = null)

            Text(text = "Upload Profile Picture", fontSize = 10.sp, color = Color.Blue)
        }

        Text(text = "Choose and Avatar", fontSize = 30.sp, color = Color.Blue)

        Row(modifier = Modifier
            .background(Color.White),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter =  painterResource(id = R.drawable.profile_male), contentDescription = null)
            Image(painter =  painterResource(id = R.drawable.profile_male), contentDescription = null)
            Image(painter =  painterResource(id = R.drawable.profile_male), contentDescription = null)
        }

        Button(onClick = navigateToHomeScreen, modifier = Modifier.fillMaxWidth().padding(30.dp)){
            Text(text = "Get Started")
        }

    }
}

