package com.sauce_hannibal.projet_android_m1cyber.ui.screens.game.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.game.GameUiState
import kotlinx.coroutines.delay

@Composable
fun GameTimerComponent(gameUiState: GameUiState, modifier: Modifier) {
    //TODO
    // 1. if timer is set to 20, then the progress bar should be full again
    // 2. if timer is set to 0, then the progress bar should be empty and the next question should be displayed
    var timeLeft by remember { mutableStateOf(gameUiState.timer) }
    LaunchedEffect(gameUiState.timer) {
        timeLeft = gameUiState.timer
    }
    LaunchedEffect(true) {
        while (timeLeft > 0) {
            delay(1000)
            timeLeft--
        }
        gameUiState.numberOfQuestionsAnswered++
    }

    val progress by animateFloatAsState(
        targetValue = timeLeft.toFloat() / 20f,
        animationSpec = tween(durationMillis = 1000)

    )

    LinearProgressIndicator(
        progress = progress,
        modifier = modifier.fillMaxWidth()
    )
}