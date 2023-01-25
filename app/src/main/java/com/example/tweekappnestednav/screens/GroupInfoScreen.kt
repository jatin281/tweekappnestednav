package com.example.tweekappnestednav.screens

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.compose.rememberNavController
import com.example.tweekappnestednav.R
import com.example.tweekappnestednav.shared.PlayerInfoCard
import com.example.tweekappnestednav.ui.theme.Orange
import com.example.tweekappnestednav.ui.theme.TweekAppNestedNavTheme

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GroupInfoScreen(
    navigateToRequestsScreen: () -> Unit
) {
    val navController = rememberNavController()
    val context = LocalContext.current

    var textFieldSearch by remember{
        mutableStateOf("")
    }

    val constraints = ConstraintSet{
        val heading = createRefFor("heading")
        val search = createRefFor("search")
        val list = createRefFor("list")
        val fab = createRefFor("fab")

        constrain(heading){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            bottom.linkTo(search.top)
            width = Dimension.matchParent
            height = Dimension.wrapContent
        }
        constrain(search){
            top.linkTo(heading.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(list.top)
            width = Dimension.matchParent
            height = Dimension.wrapContent
        }
        constrain(list){
            top.linkTo(search.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            width = Dimension.matchParent
            height = Dimension.fillToConstraints
        }
        constrain(fab){
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

        Row(modifier = Modifier
            .layoutId("heading")
            .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically){

            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(id = R.drawable.back_arrow),
                    contentDescription = null,
                    alignment = Alignment.CenterStart,
                )

                Spacer(modifier = Modifier.size(10.dp))

                MultiStyleText("Group ", Color.Black, "Name", Orange, 24.sp)
            }


            Text(text = "Requests", fontSize = 10.sp, color = Orange, textAlign = TextAlign.End, modifier = Modifier.clickable(onClick = navigateToRequestsScreen))
        }

        Box(modifier = Modifier
            .layoutId("search")){

            TextField(value = textFieldSearch,
                label ={
                    Text(text = "Search")
                },
                onValueChange = {textFieldSearch =it},
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp))
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

            Text(text = "10 Members", fontSize = 20.sp, color = Color.Black)

            LazyColumn() {
                items((1..10).toList()){
                    PlayerInfoCard()
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .layoutId("fab")
                .padding(20.dp),
            onClick = {
                //OnClick Method
            },
            containerColor = Orange,
            shape = RoundedCornerShape(16.dp),
        ) {
            Icon(
                imageVector = Icons.Rounded.Share,
                contentDescription = "Add FAB",
                tint = Color.White,
            )
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
            GroupInfoScreen(
                navigateToRequestsScreen = {},
            )
        }
    }
}