package com.example.tweekappnestednav.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeightScreen(
    navigateToHandednessScreen: () -> Unit,
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
    ){
        Text(text = "Height",fontSize = 30.sp, color = Color.Blue)

        Row(
            modifier = Modifier
                .height(300.dp)
                .background(Color.White),
//                    horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Feet & inch", fontSize = 10.sp, color = Color.Blue)

            Spacer(modifier = Modifier.width(15.dp))

            Text(text = "Centimeter", fontSize = 10.sp, color = Color.Blue)
        }

        TextField(value = textFieldState , onValueChange ={
            textFieldState =it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(80.dp))

        Text(text = "Enter Weight",fontSize = 10.sp, color = Color.Blue)


        Button(onClick = navigateToHandednessScreen,
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
        ) {
            Text(text = "Next")
        }


    }
}
