package com.example.tweekappnestednav.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun DobScreen(
    navigateToGenderScreen: () -> Unit,
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
        Text(text = "Date of Birth", fontSize = 30.sp, color = Color.Blue)

        TextField(
            value = textFieldState,
            label ={
                Text(text = "DD/MM/YYYY")
            },
            onValueChange ={
                textFieldState =it
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(30.dp))


        Button(onClick = navigateToGenderScreen, modifier = Modifier.fillMaxWidth().padding(30.dp)){
            Text(text = "Next")
        }
    }
}

//class DobCoachScreen : ComponentActivity() {
//    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//    @OptIn(ExperimentalMaterial3Api::class)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            var textFieldState by remember{
//                mutableStateOf("")
//            }
//
//            Column(modifier = Modifier
//                .fillMaxSize()
//                .background(Color.White),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.SpaceAround
//            ) {
//                Text(text = "Date of Birth", fontSize = 30.sp, color = Color.Blue)
//
//                TextField(
//                    value = textFieldState,
//                    label ={
//                        Text(text = "DD/MM/YYYY")
//                    },
//                    onValueChange ={
//                        textFieldState =it
//                    },
//                    singleLine = true,
//                    modifier = Modifier.fillMaxWidth().padding(30.dp))
//
//
//                Button(onClick ={
//                }, modifier = Modifier.fillMaxWidth().padding(30.dp)){
//                    Text(text = "Next")
//                }
//            }
//
//        }
//    }
//}