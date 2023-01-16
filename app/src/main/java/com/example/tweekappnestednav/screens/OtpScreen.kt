package com.example.tweekappnestednav.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tweekappnestednav.ui.theme.TweekAppNestedNavTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpScreen(
    navigateToEnterNameScreen: () -> Unit,
    popBackStack: () -> Unit,
    popUpToLogin: () -> Unit,
) {
    var textFieldState by remember{
        mutableStateOf("")
    }

    Column(modifier = Modifier.fillMaxSize().background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround) {
        Text(text = "OTP Verification", color = Color.Blue)

        TextField(value = textFieldState,
            label ={
                Text(text = "Enter the otp")
            },
            onValueChange = {textFieldState =it},
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(30.dp))

        Button(onClick =navigateToEnterNameScreen, modifier = Modifier.fillMaxWidth().padding(30.dp)){
            Text(text = "Continue")
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    TweekAppNestedNavTheme(useSystemUiController = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            OtpScreen(
                navigateToEnterNameScreen = {},
                popBackStack = {},
                popUpToLogin = {})
        }
    }
}

