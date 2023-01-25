package com.example.tweekappnestednav.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.tweekappnestednav.R
import com.example.tweekappnestednav.ui.theme.TweekAppNestedNavTheme

@Composable
fun PerformanceCard(img: Int, text: String, onClick : () -> Unit){


    val constraints = ConstraintSet{
        val image = createRefFor("image")
        val info = createRefFor("info")
        val date = createRefFor("date")

        constrain(image){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            bottom.linkTo(parent.bottom)
            end.linkTo(info.start)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }
        constrain(info){
            top.linkTo(parent.top)
            start.linkTo(image.end)
            end.linkTo(date.start)
            bottom.linkTo(parent.bottom)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }
        constrain(date){
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(info.end)
            end.linkTo(parent.end)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

    }

    ConstraintLayout(constraints, modifier = Modifier
        .wrapContentHeight().fillMaxWidth()
        .background(Color.White)
        .clickable(onClick = onClick)
    ) {
            Box(
                modifier = Modifier
                    .layoutId("image")
                    .size(80.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = img),
                    contentScale = ContentScale.Fit,
                    contentDescription = null
                )
            }

            Column(modifier = Modifier.layoutId("info")
            , horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center) {

                Text(text = text, fontSize = 15.sp, color = Color.Black)

                Text(text = "Analytics", fontSize = 10.sp, color = Color.LightGray)

            }

            Column(
                modifier = Modifier
                    .layoutId("date")
                    .width(90.dp)
                    .height(80.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "3:28 pm", fontSize = 15.sp, color = Color.Black)

                Text(text = "10 seconds ago", fontSize = 10.sp, color = Color.LightGray)

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
            PerformanceCard(
                R.drawable.ball,
                "Ball 4",
                onClick = {}
            )
        }
    }
}

