package com.example.tweekappnestednav.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
fun PlayerInfoCard(){


    val constraints = ConstraintSet{
        val image = createRefFor("image")
        val info = createRefFor("info")
        val dots = createRefFor("dots")

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
            end.linkTo(dots.start)
            bottom.linkTo(parent.bottom)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }
        constrain(dots){
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
    ) {
            Box(
                modifier = Modifier
                    .layoutId("image")
                    .size(80.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.player),
                    contentScale = ContentScale.Fit,
                    contentDescription = null
                )
            }

            Column(modifier = Modifier.layoutId("info")
            , horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center) {

                Text(text = "Player Name", fontSize = 15.sp, color = Color.Black)

                Text(text = "1234567890", fontSize = 10.sp, color = Color.LightGray)

            }

            Box(
                modifier = Modifier
                    .layoutId("dots")
                    .size(80.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.menu),
                    contentScale = ContentScale.Fit,
                    contentDescription = null
                )
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
            PlayerInfoCard(

            )
        }
    }
}

