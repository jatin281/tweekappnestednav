package com.example.tweekappnestednav.shared

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tweekappnestednav.ui.theme.Orange
import com.example.tweekappnestednav.ui.theme.TweekAppNestedNavTheme
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryAnalyticsCard(score: Int, cardName: String){

    Card(
        modifier = Modifier
            .wrapContentSize(),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {

        Column(
            modifier = Modifier
                .width(110.dp)
                .height(143.dp)
                ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            CircularProgressBar((score.toFloat())/100)

            println((score.toFloat())/100)
                Spacer(modifier = Modifier.height(5.dp))
            Text(text = cardName, fontSize = 14.sp, color = Color.Black, textAlign = TextAlign.Center, maxLines = 2)
        }

    }
}

@Composable
fun CircularProgressBar(
    percentage: Float,
    radius: Dp = 40.dp,
    animationDuration: Int = 1000,
) {

    var animFinished by remember {
        mutableStateOf(false)
    }

    val current_percent = animateFloatAsState(
        targetValue = if (animFinished) percentage else 0f,
        animationSpec = tween(
            durationMillis = animationDuration,
        )
    )

    LaunchedEffect(key1 = true) {
        animFinished = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2) // diameter
    ) {

        Canvas(modifier = Modifier.size(radius * 2)) {
            drawArc(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFF76349),
                        Color(0xFFF76349),
                        Color(0xFFF76349)
                    )
                ),
                startAngle = -90f,
                sweepAngle = 360 * current_percent.value,
                useCenter = false,
                style = Stroke(
                    4.dp.toPx(),
                    cap = StrokeCap.Round,
                ),
            )
        }
        Text(text = (percentage.absoluteValue * 100).toInt().toString(), fontSize = 16.sp, fontWeight = Bold, color = Color.Black)

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
            SummaryAnalyticsCard(88,"overall \nScore")
        }
    }
}

