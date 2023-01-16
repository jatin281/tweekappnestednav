package com.example.tweekappnestednav.screens

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
import com.example.tweekappnestednav.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HandednessScreen(
    navigateToProfilePicScreen: () -> Unit,
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
        Text(text = "Handedness", fontSize = 30.sp, color = Color.Blue)

        Batting()
        Bowling()

        Button(onClick = navigateToProfilePicScreen,
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
        ) {
            Text(text = "Next")
        }


    }
}

//class HandednessScreen: ComponentActivity() {
//    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//    @OptIn(ExperimentalMaterial3Api::class)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//
//            Column(modifier = Modifier
//                .fillMaxSize()
//                .background(Color.White),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.SpaceAround
//            ){
//                Text(text = "Handedness", fontSize = 30.sp, color = Color.Blue)
//
//                Batting()
//                Bowling()
//
//                Button(onClick = { /*TODO*/ },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(30.dp)
//                ) {
//                    Text(text = "Next")
//                }
//
//
//            }
//
//        }
//    }
//}

@Composable
fun Batting(){
    Column(modifier = Modifier
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Batting", fontSize = 15.sp, color = Color.Blue)

        Row(
            modifier = Modifier
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.left), contentDescription = null)
            Image(painter = painterResource(id = R.drawable.right), contentDescription = null)
        }

    }
}

@Composable
fun Bowling(){
    Column(modifier = Modifier
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Bowling", fontSize = 15.sp, color = Color.Blue)

        Row(
            modifier = Modifier
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.left), contentDescription = null)
            Image(painter = painterResource(id = R.drawable.right), contentDescription = null)
        }

    }
}