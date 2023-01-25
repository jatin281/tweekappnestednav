package com.example.tweekappnestednav.screens

import android.os.Build
import android.widget.HorizontalScrollView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tweekappnestednav.shared.SummaryAnalyticsCard
import com.example.tweekappnestednav.ui.theme.TweekAppNestedNavTheme


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AnalyticsBowlingScreen(
    navController: NavHostController = rememberNavController()
) {
    var textFieldSearch by remember{
        mutableStateOf("")
    }

    val textList = ArrayList<String>()//Creating an empty arraylist
    textList.add("Overall score")//Adding object in arraylist
    textList.add("Run-up")
    textList.add("Jump")
    textList.add("Back-Foot contact")
    textList.add("Front-Foot contact")
    textList.add("Release")


    val card = (0..5).toList()

    val constraints = ConstraintSet{
        val heading = createRefFor("heading")
        val bowling = createRefFor("bowling")
        val cards = createRefFor("cards")

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
            bottom.linkTo(cards.top)
            width = Dimension.matchParent
            height = Dimension.wrapContent
        }
        constrain(cards){
            top.linkTo(bowling.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            width = Dimension.matchParent
            height = Dimension.fillToConstraints
        }

    }

    ConstraintLayout(constraints, modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        Box(modifier = Modifier
            .layoutId("heading")
            .padding(20.dp)){

            Text(text = "Summary", fontSize = 30.sp, color = Color.Blue)
        }

        LazyRow(modifier = Modifier
            .layoutId("bowling")
            .padding(20.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically){

            items((1..5).toList()) {
                Text(text = textList[it], fontSize = 15.sp, color = Color.Blue)
                Spacer(modifier = Modifier.size(10.dp))
            }

//            Text(text = "General", fontSize = 15.sp, color = Color.Blue)
//            Spacer(modifier = Modifier.size(10.dp))
//            Text(text = "Run-Up", fontSize = 15.sp, color = Color.Blue)
//            Spacer(modifier = Modifier.size(10.dp))
//            Text(text = "Jump", fontSize = 15.sp, color = Color.Blue)
//            Spacer(modifier = Modifier.size(10.dp))
//            Text(text = "Back-Foot Contact", fontSize = 15.sp, color = Color.Blue)
//            Spacer(modifier = Modifier.size(10.dp))
//            Text(text = "Front-Foot Contact", fontSize = 15.sp, color = Color.Blue)
//            Spacer(modifier = Modifier.size(10.dp))
//            Text(text = "Release", fontSize = 15.sp, color = Color.Blue)

        }

        Box(modifier = Modifier
            .layoutId("cards")
            .fillMaxWidth()){

            LazyHorizontalGrid(
                rows = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                items(card.size) {
//                    SummaryAnalyticsCard(score = scoreList[it], cardName = textList[it])
                }
            }

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
            AnalyticsBowlingScreen(

            )

        }
    }
}