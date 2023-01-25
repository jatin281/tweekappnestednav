package com.example.tweekappnestednav.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tweekappnestednav.R
import com.example.tweekappnestednav.shared.PerformanceCard
import com.example.tweekappnestednav.shared.PlayerInfoCard
import com.example.tweekappnestednav.shared.PlayerScoreCard
import com.example.tweekappnestednav.shared.SessionHistoryCard
import com.example.tweekappnestednav.shared.SessionSummaryCard
import com.example.tweekappnestednav.ui.theme.Orange
import com.example.tweekappnestednav.ui.theme.TweekAppNestedNavTheme


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SessionInfoScreen(
    id: Int,
    navigateToSessionSummaryScreen: () -> Unit,
    navigateToAnalyticsBowlingScreen: () -> Unit
) {
    var textFieldSearch by remember{
        mutableStateOf("")
    }

    val constraints = ConstraintSet{
        val heading = createRefFor("heading")
        val summary = createRefFor("summary")
        val list = createRefFor("list")

        constrain(heading){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            bottom.linkTo(summary.top)
            width = Dimension.matchParent
            height = Dimension.wrapContent
        }
        constrain(summary){
            top.linkTo(heading.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(list.top)
            width = Dimension.matchParent
            height = Dimension.wrapContent
        }
        constrain(list){
            top.linkTo(summary.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            width = Dimension.wrapContent
            height = Dimension.fillToConstraints
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
        ){

            Image(
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = null,
                alignment = Alignment.CenterStart,
            )

            Spacer(modifier = Modifier.size(10.dp))

            MultiStyleText("Session ", Color.Black, "#$id", Orange, 24.sp)

        }

        Box(modifier = Modifier
            .layoutId("summary")){

            SessionSummaryCard(onClick = navigateToSessionSummaryScreen)

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

            Text(text = "Performance", fontSize = 30.sp, color = Color.Black)

            LazyColumn() {
                items((1..10).toList()){

                    if (id == 0){
                        PerformanceCard(R.drawable.ball,"Ball 5",onClick = navigateToAnalyticsBowlingScreen)
                        Divider(color = Color.Black, thickness = 0.5.dp)
                    }else if(id == 1){
                        PerformanceCard(R.drawable.bat,"Shot 5",onClick = {})
                        Divider(color = Color.Black, thickness = 0.5.dp)
                    }

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
            SessionInfoScreen(id = 2,
                navigateToSessionSummaryScreen = {},
                navigateToAnalyticsBowlingScreen = {}

            )

        }
    }
}