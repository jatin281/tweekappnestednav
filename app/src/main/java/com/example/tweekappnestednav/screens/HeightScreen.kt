package com.example.tweekappnestednav.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.tweekappnestednav.R
import com.example.tweekappnestednav.shared.DefaultButton
import com.example.tweekappnestednav.ui.theme.Orange
import com.example.tweekappnestednav.ui.theme.TextGrey
import com.example.tweekappnestednav.ui.theme.TweekAppNestedNavTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeightScreen(
    navigateToHandednessScreen: () -> Unit,
    popBackStack: () -> Unit,
) {
    var textFieldState by remember{
        mutableStateOf("")
    }

    val constraints = ConstraintSet {
        val heading = createRefFor("heading")
        val text = createRefFor("text")
        val textbox = createRefFor("textbox")
        val button = createRefFor("button")

        constrain(heading) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.matchParent
            height = Dimension.wrapContent
        }
        constrain(text) {
            top.linkTo(heading.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.matchParent
            height = Dimension.wrapContent
        }
        constrain(textbox) {
            top.linkTo(text.bottom)
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

            Text(text = "Height", fontSize = 24.sp, color = Color.Black, fontFamily = FontFamily.SansSerif)

        }

        Row(
            modifier = Modifier
                .layoutId("text")
                .padding(20.dp),
        ) {
            Text(text = "Feet & inch", fontSize = 16.sp, color = TextGrey)

            Spacer(modifier = Modifier.width(15.dp))

            Text(text = "Centimeter", fontSize = 16.sp, color = TextGrey)

        }

        Column(
            modifier = Modifier
                .layoutId("textbox"),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextField(
                value = textFieldState,
                label = {
                    Text(text = "Enter Height", fontFamily = FontFamily.SansSerif, fontSize = 14.sp, textAlign = TextAlign.Center)
                },
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    containerColor = Color.White
                ),
                onValueChange = {
                    textFieldState = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Done),
                singleLine = true,
                shape = RoundedCornerShape(38.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .width(90.dp)
                    .padding(30.dp).border(2.dp, Orange, RoundedCornerShape(38.dp))
            )


        }

        Column(
            modifier = Modifier
                .padding(20.dp)
                .layoutId("button")
        ) {

            DefaultButton(text = "Next", onClick = navigateToHandednessScreen )

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
            HeightScreen(
                navigateToHandednessScreen = {},
                popBackStack = {},
            )
        }
    }
}

