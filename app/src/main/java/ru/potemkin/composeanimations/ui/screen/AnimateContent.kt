package ru.potemkin.composeanimations.ui.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun AnimateContent() {

    var isFirstScreenLaunched by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { isFirstScreenLaunched = !isFirstScreenLaunched }
        ) {
            Text(text = "Switch screens")
        }

        AnimatedContent(
            targetState = isFirstScreenLaunched,
            transitionSpec = {
                if(targetState){
                slideIn(tween(2000)){
                    IntOffset(0,-it.height)
                } togetherWith slideOut(tween(2000)){
                    IntOffset(0,-it.height)
                }}else{
                    slideInHorizontally(tween(2000)){
                        it
                    } togetherWith slideOutHorizontally(tween(2000)){
                        it
                    }
                }
            }
        ) { shouldLaunchFirstScreen ->
            if (shouldLaunchFirstScreen) {
                Screen1()
            } else {
                Screen2()
            }
        }
    }
}

@Composable
private fun Screen1() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    )
}

@Composable
private fun Screen2() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    )
}