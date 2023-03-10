package com.example.tweekappnestednav.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.tweekappnestednav.R
import com.example.tweekappnestednav.shared.DefaultButton
import com.example.tweekappnestednav.shared.PlayerScoreCard
import com.example.tweekappnestednav.shared.SessionHistoryCard
import com.example.tweekappnestednav.ui.theme.Orange
import com.example.tweekappnestednav.ui.theme.TextGrey
import com.example.tweekappnestednav.ui.theme.TweekAppNestedNavTheme


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    navigateToSessionInfoScreen: (Any?) -> Unit,
) {
    var textFieldSearch by remember{
        mutableStateOf("")
    }
    val context = LocalContext.current

    var batting = remember { mutableStateOf(0) }

    var bowlColor = remember { mutableStateOf( Orange) }
    var batColor = remember { mutableStateOf( TextGrey) }

    val constraints = ConstraintSet{
        val heading = createRefFor("heading")
        val bowling = createRefFor("bowling")
        val score = createRefFor("score")
        val list = createRefFor("list")
        val button = createRefFor("button")

        constrain(heading){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            bottom.linkTo(bowling.top)
            width = Dimension.matchParent
            height = Dimension.wrapContent
        }
        constrain(bowling){
            top.linkTo(heading.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(score.top)
            width = Dimension.matchParent
            height = Dimension.wrapContent
        }
        constrain(score){
            top.linkTo(bowling.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(list.top)
            width = Dimension.matchParent
            height = Dimension.wrapContent
        }
        constrain(list){
            top.linkTo(score.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            width = Dimension.wrapContent
            height = Dimension.fillToConstraints
        }
        constrain(button){
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

    }

    ConstraintLayout(constraints, modifier = Modifier
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

            MultiStyleText("Anant ", Color.Black, "Sharma", Orange, 24.sp)

        }

        Row(modifier = Modifier
            .layoutId("bowling")
            .padding(20.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically){

            Text(
                text = "Bowling",
                fontSize = 16.sp,
                color = bowlColor.value,
                modifier = Modifier
                    .clickable(onClick = {
                        batting.value = 0
                        bowlColor.value = Orange
                        batColor.value = TextGrey
                    })
                    )

            Spacer(modifier = Modifier.size(10.dp))

            Text(
                text = "Batting",
                fontSize = 16.sp,
                color = batColor.value,
                modifier = Modifier.clickable(onClick = {
                    batting.value = 1
                    bowlColor.value = TextGrey
                    batColor.value = Orange
                } ))

        }

        Box(modifier = Modifier
            .layoutId("score")){

            PlayerScoreCard()

        }

        Column(modifier = Modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )
            .shadow(
                20.dp,
                spotColor = Color.White,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )
            .padding(20.dp)
            .layoutId("list")
            .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))){

            Text(text = "Session History", fontSize = 30.sp, color = Color.Black)


            LazyColumn() {

                items((1..10).toMutableList()){

                    if (batting.value == 0){
                        SessionHistoryCard(R.drawable.bowling,"6 Balls",onClick = { navigateToSessionInfoScreen(batting.value) })
                        Divider(color = Black, thickness = 0.5.dp)

                    }else if(batting.value == 1){
                        SessionHistoryCard(R.drawable.batting,"6 Shots",onClick = {navigateToSessionInfoScreen(batting.value) })
                        Divider(color = Black, thickness = 0.5.dp)
                    }

                }
            }
        }

        Box(
            modifier = Modifier
                .layoutId("button")
                .fillMaxWidth()
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ){

            DefaultButton(text = "Start Bowling", onClick = { } )

        }

    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    TweekAppNestedNavTheme(useSystemUiController = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen {}

        }
    }
}
