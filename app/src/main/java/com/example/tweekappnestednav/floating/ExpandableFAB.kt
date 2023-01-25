package com.example.tweekappnestednav.floating

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.tweekappnestednav.R
import com.example.tweekappnestednav.ui.theme.Orange
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalAnimationApi::class, androidx.compose.ui.ExperimentalComposeUiApi::class)
@Composable
fun ExpandableFAB(
    createOrganization: () -> Unit,
    joinOrganization: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isFabClicked = remember { mutableStateOf(false) }
    val animationSpec: AnimationSpec<Float> = spring(
        stiffness = Spring.StiffnessLow,
        dampingRatio = Spring.DampingRatioLowBouncy
    )
    val density = LocalDensity.current.density
    val offsetDeleteY = animateFloatAsState(
        targetValue = if (isFabClicked.value) -250f * density / 2.75f else 0f,
        animationSpec = animationSpec
    )
    val offsetAddNewY = animateFloatAsState(
        targetValue = if (isFabClicked.value) -500f * density / 2.75f else 0f,
        animationSpec = animationSpec
    )
    val rotationAngle = animateFloatAsState(
        targetValue = if (isFabClicked.value) 90f else 0f,
        animationSpec = animationSpec
    )

    val plusFabBackgroundColor = animateColorAsState(
        animationSpec = tween(durationMillis = 200),
        targetValue = if (isFabClicked.value) White else Color.Black
    )

    val plusFabIconColor = animateColorAsState(
        animationSpec = tween(durationMillis = 200),
        targetValue = if (isFabClicked.value) Color.Black else White
    )
    AnimatedVisibility(
        enter = fadeIn(),
        exit = fadeOut(),
        visible = isFabClicked.value
    ) {
        Box(modifier = Modifier
            .pointerInteropFilter {
                isFabClicked.value = !isFabClicked.value
                false
            }
            .alpha(0.8f)
            .background(White)
            .fillMaxSize())
    }

    Box(modifier = modifier) {

        FABItem(
            offset = IntOffset(0, offsetAddNewY.value.roundToInt()),
            onClick = {
                isFabClicked.value = !isFabClicked.value
                joinOrganization()
            },
            size = 25.dp,
            resId = R.drawable.fab_join,
            backgroundColor = White,
            colorFilter = ColorFilter.tint(Color.Black)

        )

        FABItem(
            offset = IntOffset(0, offsetDeleteY.value.roundToInt()),
            onClick = {
                isFabClicked.value = !isFabClicked.value
                createOrganization()
            },
            size = 25.dp,
            resId = R.drawable.fab_create,
            backgroundColor = White,
            colorFilter = ColorFilter.tint(Color.Black)

        )

        FABItem(
            modifier = Modifier.rotate(rotationAngle.value),
            onClick = {
                isFabClicked.value = !isFabClicked.value
            },
            size = 25.dp,
            resId = R.drawable.fab_plus,
            backgroundColor = Orange,
            colorFilter = ColorFilter.tint(plusFabIconColor.value)

        )

    }
}