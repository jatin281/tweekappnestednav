package com.example.tweekappnestednav.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.tweekappnestednav.R
import com.example.tweekappnestednav.shared.DefaultButton
import com.example.tweekappnestednav.shared.UserCard
import com.example.tweekappnestednav.ui.theme.Orange
import com.example.tweekappnestednav.ui.theme.TextGrey
import com.example.tweekappnestednav.ui.theme.TweekAppNestedNavTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JoinOrgScreen(

) {
    var textFieldState by remember{
        mutableStateOf("")
    }

    val constraints = ConstraintSet {
        val heading = createRefFor("heading")
        val textbox = createRefFor("textbox")
        val card = createRefFor("card")
        val button = createRefFor("button")

        constrain(heading) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.matchParent
            height = Dimension.wrapContent
        }
        constrain(textbox) {
            top.linkTo(heading.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(card.top)
            width = Dimension.matchParent
            height = Dimension.wrapContent
        }
        constrain(card) {
            top.linkTo(textbox.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(button.top)
            width = Dimension.matchParent
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

            MultiStyleText("Join ", Color.Black, "Group", Orange, 24.sp)

        }

        Column(
            modifier = Modifier
                .layoutId("textbox")
                .padding(20.dp),
        ) {

            TextField(value = textFieldState,
                label ={
                    Text(text = "Enter Code")
                },
                onValueChange = {textFieldState =it},
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp))

        }

        Column(
            modifier = Modifier
                .layoutId("card"),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val commentsAlpha = if (textFieldState == "1234") 1f else 0f

            Column(modifier = Modifier
                .background(Color.Gray)
                .height(300.dp)
                .fillMaxWidth()
                .alpha(commentsAlpha),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                UserCard("Karnataka Institute of Cricket (KIOC)","Under 17 Years","Ross Edwards", onClick = {})

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(text = "Name", fontSize = 10.sp, color = Color.Blue)
                        Text(text = "Batch", fontSize = 10.sp, color = Color.Blue)
                        Text(text = "Coach", fontSize = 10.sp, color = Color.Blue)

                    }

                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(text = "Karnataka Institute of Cricket (KIOC)", fontSize = 10.sp, color = Color.Blue)
                        Text(text = "Under 17 Years", fontSize = 10.sp, color = Color.Blue)
                        Text(text = "Ross Edwards", fontSize = 10.sp, color = Color.Blue)

                    }

                }
            }

        }

        Column(
            modifier = Modifier
                .padding(20.dp)
                .layoutId("button")
        ) {

            DefaultButton(text = "Join", onClick = {} )

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
            JoinOrgScreen(

            )
        }
    }
}

