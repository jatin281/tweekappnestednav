package com.example.tweekappnestednav.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.compose.rememberNavController
import com.example.tweekappnestednav.R
import com.example.tweekappnestednav.shared.UserCard
import com.example.tweekappnestednav.floating.ExpandableFAB
import com.example.tweekappnestednav.ui.theme.Orange
import com.example.tweekappnestednav.ui.theme.TweekAppNestedNavTheme

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GroupsScreen(
    navigateToJoinOrgScreen: () -> Unit,
    navigateToCreateOrgScreen: () -> Unit,
    navigateToGroupInfoScreen: () -> Unit
) {
    val navController = rememberNavController()
    val context = LocalContext.current

    val constraints = ConstraintSet{
        val heading = createRefFor("heading")
        val list = createRefFor("list")
        val fab = createRefFor("fab")

        constrain(heading){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            bottom.linkTo(list.top)
            width = Dimension.matchParent
            height = Dimension.wrapContent
        }
        constrain(list){
            top.linkTo(heading.bottom)
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

            MultiStyleText("My ", Color.Black, "Groups", Orange, 24.sp)

        }
        Box(modifier = Modifier
            .layoutId("list")){

            LazyColumn() {
                items((1..10).toList()){
                    UserCard("name","nickname", "address", onClick = navigateToGroupInfoScreen)
                }
            }
        }

        ExpandableFAB(modifier = Modifier
            .padding(bottom = 50.dp)
            .layoutId("fab"),
            joinOrganization = navigateToJoinOrgScreen,
            createOrganization = navigateToCreateOrgScreen
        )

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
            GroupsScreen(
                navigateToJoinOrgScreen = {},
                navigateToCreateOrgScreen = {},
                navigateToGroupInfoScreen = {}
            )

        }
    }
}
