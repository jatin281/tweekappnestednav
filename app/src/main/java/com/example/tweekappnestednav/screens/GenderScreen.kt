package com.example.tweekappnestednav.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tweekappnestednav.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderScreen(
    navigateToWeightScreen: () -> Unit,
    popBackStack: () -> Unit,
    popUpToLogin: () -> Unit,
) {
    var textFieldState by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = "Gender", fontSize = 30.sp, color = Color.Blue)

        Row(
            modifier = Modifier
                .height(300.dp)
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImgMale()
            ImgFemale()
        }

        Button(onClick = navigateToWeightScreen,
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
        ) {
            Text(text = "Next")
        }

    }
}


@Composable
fun ImgMale() {

    Column(
        modifier = Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val img = painterResource(id = R.drawable.player)

        Image(
            img,
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
        )
        
        Text(text = "Male", fontSize = 10.sp, color = Color.Blue)

    }
}

@Composable
fun ImgFemale() {

    Column(
        modifier = Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val img = painterResource(id = R.drawable.coach)

//        val modifier = Modifier.preferredHeight(height = Dp(200F)).preferredWidth(width = Dp(200F))

        Image(
            img,
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
        )

        Text(text = "Female", fontSize = 10.sp, color = Color.Blue )

    }
}