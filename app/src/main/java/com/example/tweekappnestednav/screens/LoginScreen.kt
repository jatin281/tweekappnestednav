package com.example.tweekappnestednav.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import com.example.tweekappnestednav.ui.theme.TweekAppNestedNavTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun LoginScreen(
    navigateToOtp: () -> Unit
) {
    var textFieldState by remember {
        mutableStateOf("")
    }

    val constraints = ConstraintSet {
        val heading = createRefFor("heading")
        val welcome = createRefFor("welcome")


        constrain(heading) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(welcome.top)
            width = Dimension.matchParent
            height = Dimension.wrapContent
        }
        constrain(welcome) {
//            top.linkTo(heading.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            width = Dimension.matchParent
            height = Dimension.wrapContent
        }
    }

    ConstraintLayout(
        constraints, modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Box(
            modifier = Modifier
                .layoutId("heading")
                .background(color = Color.LightGray)

        ) {

            Image(painter = painterResource(id = R.drawable.login_image), contentDescription = null)
        }

        Column(
            modifier = Modifier
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
                .layoutId("welcome")
                .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
        )
        {
            Text(
                text = "Welcome !",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(10.dp)
            )

            TextField(
                value = textFieldState,
                label = {
                    Text(text = "Enter your number", fontFamily = FontFamily.SansSerif)
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                ),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = null, tint = Color.LightGray)
                },
                onValueChange = {
                    textFieldState = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Done),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
            )

                DefaultButton(text = "Login", onClick = navigateToOtp )


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
            LoginScreen(
                navigateToOtp = {}
            )
        }
    }
}