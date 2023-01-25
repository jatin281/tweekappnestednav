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
import androidx.compose.ui.draw.alpha
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
import com.example.tweekappnestednav.shared.DefaultButton
import com.example.tweekappnestednav.shared.GroupCard
import com.example.tweekappnestednav.shared.UserCard
import com.example.tweekappnestednav.ui.theme.Orange
import com.example.tweekappnestednav.ui.theme.TweekAppNestedNavTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateOrgScreen(

) {

    var textFieldName by remember{
        mutableStateOf("")
    }

    var commentsAlpha  by remember{
        mutableStateOf(0f)
    }

    val navController = rememberNavController()


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

            MultiStyleText("New ", Color.Black, "Group", Orange, 24.sp)

        }

        Column(
            modifier = Modifier
                .layoutId("textbox")
                .padding(20.dp),
        ) {

            TextField(value = textFieldName,
                label ={
                    Text(text = "Enter Code")
                },
                onValueChange = {textFieldName =it},
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

            Column(modifier = Modifier
                .background(Color.Gray)
                .height(300.dp)
                .fillMaxWidth()
                .alpha(commentsAlpha),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
               GroupCard(textFieldName, onClick = {})

            }

        }

        Column(
            modifier = Modifier
                .padding(20.dp)
                .layoutId("button")
        ) {

            DefaultButton(text = "Create", onClick = {commentsAlpha = if (textFieldName.isNotEmpty()) 1f else 0f} )

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
            CreateOrgScreen(

            )
        }
    }
}

