package com.example.tweekappnestednav.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.tweekappnestednav.shared.UserCard
import com.example.tweekappnestednav.ui.theme.TweekAppNestedNavTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditOrgScreen(

) {
    var textFieldName by remember{
        mutableStateOf("")
    }
    var textFieldNickName by remember{
        mutableStateOf("")
    }
    var textFieldAddress by remember{
        mutableStateOf("")
    }

    val constraints = ConstraintSet{
        val heading = createRefFor("heading")
        val card = createRefFor("card")
        val code = createRefFor("code")
        val button = createRefFor("button")

        constrain(heading){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(card.top)
            width = Dimension.matchParent
            height = Dimension.wrapContent
        }
        constrain(card){
            top.linkTo(heading.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(code.top)
            width = Dimension.matchParent
            height = Dimension.wrapContent
        }
        constrain(code){
            top.linkTo(card.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(button.top)
            width = Dimension.matchParent
            height = Dimension.value(100.dp)
        }
        constrain(button){
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(code.bottom)
            bottom.linkTo(parent.bottom)
            width = Dimension.matchParent
            height = Dimension.wrapContent
        }

    }

    ConstraintLayout(constraints, modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(20.dp)) {

        Box(modifier = Modifier
            .layoutId("heading")){

            Text(text = "Edit Organization", fontSize = 30.sp, color = Color.Blue)
        }
        Column(
            modifier = Modifier.wrapContentHeight().layoutId("card")
        ){

            UserCard(textFieldName,textFieldNickName,textFieldAddress, onClick = {})

            TextField(value = textFieldName,
                label ={
                    Text(text = "Name")
                },
                onValueChange = {textFieldName =it},
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp))

            TextField(value = textFieldNickName,
                label ={
                    Text(text = "Nickname")
                },
                onValueChange = {textFieldNickName =it},
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp))

            TextField(value = textFieldAddress,
                label ={
                    Text(text = "Address")
                },
                onValueChange = {textFieldAddress =it},
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp))
        }

        Box(modifier = Modifier.background(Color.Gray)
            .layoutId("code").padding(10.dp)){

            Text(text = "Joining code", fontSize = 15.sp, color = Color.Blue, modifier = Modifier.align(Alignment.TopStart))

            Text(text = "6v71yz", fontSize = 25.sp, color = Color.Blue, modifier = Modifier.align(Alignment.CenterStart))
        }

        Row(modifier = Modifier
            .layoutId("button")
        ,horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically){

            Button(onClick = {}, modifier = Modifier.padding(30.dp)){
                Text(text = "delete")
            }

            Button(onClick = {}, modifier = Modifier.padding(30.dp)){
                Text(text = "Save")
            }
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
            EditOrgScreen(

            )
        }
    }
}

