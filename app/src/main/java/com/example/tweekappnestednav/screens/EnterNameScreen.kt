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
import androidx.compose.ui.unit.sp
import com.example.tweekappnestednav.graphs.PlayerDetails
import com.example.tweekappnestednav.graphs.playerDetailsNavGraph
import com.example.tweekappnestednav.ui.theme.TweekAppNestedNavTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterNameScreen(
    navigateToDobScreen: () -> Unit,
    popBackStack: () -> Unit,
) {
    var textFieldState by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = "Enter Name", fontSize = 30.sp, color = Color.Blue)

        TextField(
            value = textFieldState,
            label ={
                Text(text = "First name")
            },
            onValueChange ={
                textFieldState =it
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(30.dp))

        TextField(
            value = textFieldState,
            label ={
                Text(text = "Last name")
            },
            onValueChange ={
                textFieldState =it
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(30.dp))

        Button(onClick = navigateToDobScreen, modifier = Modifier.fillMaxWidth().padding(30.dp)){
            Text(text = "Next")
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
            EnterNameScreen(
                navigateToDobScreen = {},
                popBackStack = {},
            )
        }
    }
}


//class EnterNameCoachScreen : ComponentActivity() {
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
//                    .fillMaxSize()
//                    .background(Color.White),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.SpaceAround
//            ) {
//                Text(text = "Enter Name", fontSize = 30.sp, color = Color.Blue)
//
//                TextField(
//                    value = textFieldState,
//                    label ={
//                        Text(text = "First name")
//                    },
//                    onValueChange ={
//                        textFieldState =it
//                    },
//                    singleLine = true,
//                    modifier = Modifier.fillMaxWidth().padding(30.dp))
//
//                TextField(
//                    value = textFieldState,
//                    label ={
//                        Text(text = "Last name")
//                    },
//                    onValueChange ={
//                        textFieldState =it
//                    },
//                    singleLine = true,
//                    modifier = Modifier.fillMaxWidth().padding(30.dp))
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