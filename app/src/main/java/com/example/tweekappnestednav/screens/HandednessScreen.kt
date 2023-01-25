package com.example.tweekappnestednav.screens

import android.service.autofill.OnClickAction
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.tweekappnestednav.R
import com.example.tweekappnestednav.shared.DefaultButton
import com.example.tweekappnestednav.ui.theme.Orange
import com.example.tweekappnestednav.ui.theme.TweekAppNestedNavTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HandednessScreen(
    navigateToHomeScreen: () -> Unit,
    popBackStack: () -> Unit,

) {
    var textFieldState by remember{
        mutableStateOf("")
    }

    var selected : Int = 0

    val constraints = ConstraintSet {
        val heading = createRefFor("heading")
        val batting = createRefFor("batting")
        val bowling = createRefFor("bowling")
        val button = createRefFor("button")

        constrain(heading) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.matchParent
            height = Dimension.wrapContent
        }
        constrain(batting) {
            top.linkTo(heading.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(bowling.top)
            width = Dimension.matchParent
            height = Dimension.wrapContent
        }
        constrain(bowling) {
            top.linkTo(batting.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(button.top)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }
        constrain(button) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

//        createVerticalChain(heading, text, textbox, button, chainStyle = ChainStyle.Spread)

    }

    ConstraintLayout(
        constraints, modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .layoutId("heading")
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = null,
                alignment = Alignment.CenterStart,
            )

            Spacer(modifier = Modifier.size(10.dp))

            Text(text = "Handedness", fontSize = 24.sp, color = Color.Black, fontFamily = FontFamily.SansSerif)

        }

        Box(
            modifier = Modifier
            .layoutId("batting"),
        contentAlignment = Alignment.Center) {

            Batting(onClick = {})

        }

        Box(
            modifier = Modifier
                .layoutId("bowling"),
            contentAlignment = Alignment.Center) {

            Bowling(onClick = {})

        }


        Column(
            modifier = Modifier
                .padding(20.dp)
                .layoutId("button")
        ) {

            DefaultButton(text = "Next", onClick = navigateToHomeScreen )

        }

    }

}


@Composable
fun Batting(
    onClick: () -> Unit
){
    Column(modifier = Modifier
        .background(Color.White)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Batting", fontSize = 16.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LeftHand(onImageClick = {})
            RightHand(onImageClick = {})
        }

    }
}

@Composable
fun Bowling(
    onClick: () -> Unit
){
    Column(modifier = Modifier
        .background(Color.White)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Bowling", fontSize = 16.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LeftHand(onImageClick = {})
            RightHand(onImageClick = {})
        }

    }
}

@Composable
fun LeftHand(
    onImageClick: () -> Unit
){
    Image(
        painter = painterResource(R.drawable.left_hand),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(90.dp)
            .clip(CircleShape)
            .clickable(enabled = true, onClick = onImageClick)
            .border(2.dp, Color.White, CircleShape)
    )
}

@Composable
fun RightHand(
    onImageClick: () -> Unit
){
    Image(
        painter = painterResource(R.drawable.right_hand),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(90.dp)
            .clip(CircleShape)
            .border(2.dp, Color.White, CircleShape)
            .clickable(enabled = true, onClick = onImageClick)
    )
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    TweekAppNestedNavTheme(useSystemUiController = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HandednessScreen(
                navigateToHomeScreen = {},
                popBackStack = {},
            )
        }
    }
}
