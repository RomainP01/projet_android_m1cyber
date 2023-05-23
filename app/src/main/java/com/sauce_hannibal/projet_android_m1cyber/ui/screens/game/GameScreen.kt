package com.sauce_hannibal.projet_android_m1cyber.ui.screens.game

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sauce_hannibal.projet_android_m1cyber.R
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.HomeRoute
import kotlinx.coroutines.delay

@Composable
fun GameScreen(
    navController: NavHostController
    ) {
    val viewModel = hiltViewModel<GameViewModel>()
    val gameUiState = viewModel.gameUiState.collectAsState().value

    var timeLeft by remember { mutableStateOf(gameUiState.timer) }
    LaunchedEffect(key1 = gameUiState.answerSelected, block = {
        timeLeft = gameUiState.timer
    })

    LaunchedEffect(key1 = gameUiState.isEnded, block = {
        while (!gameUiState.isEnded) {
            if (timeLeft == 0) {
                delay(1000)
                viewModel.handleTimerEnd()
                timeLeft = gameUiState.timer
            } else {
                delay(1000)
                timeLeft--
            }
        }
    })
    val progress by animateFloatAsState(
        targetValue = if (gameUiState.answerSelected != null) {
            1f
        } else {
            timeLeft.toFloat() / gameUiState.timer.toFloat()
        },
        animationSpec = if (gameUiState.answerSelected != null) {
            tween(durationMillis = 0)
        } else {
            tween(durationMillis = 1000)
        }
    )


    val multiplierString = if (gameUiState.multiplier % 1.0 == 0.0) {
        gameUiState.multiplier.toInt().toString()
    } else {
        gameUiState.multiplier.toString()
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {navController.navigate(HomeRoute.HOME)}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "arrow icon",
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Question ${gameUiState.numberOfQuestionsAnswered + 1}/${gameUiState.numberOfQuestions}",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Score: ${gameUiState.userScore}",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        gameUiState.currentQuestion?.let {
            Text(
                text = it.difficulty,
                modifier = Modifier
                    .padding(8.dp),
                color = viewModel.changeColorOfDifficulty(it.difficulty)
            )
        }

        Text(
            text = "x$multiplierString",
            modifier = Modifier
                .padding(8.dp)
                .background(Color.White)
                .padding(4.dp),
            color = Color.Black,
            fontSize = (16 + (gameUiState.multiplier * 3)).sp
        )
        gameUiState.currentQuestion?.let {
            Text(
                text = it.question,
                modifier = Modifier.padding(16.dp)
            )
        }

        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth()
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            items(gameUiState.possibleAnswers.size) { index ->
                Button(
                    onClick = {
                        viewModel.onAnswerClick(gameUiState.possibleAnswers[index])
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .background(color = viewModel.changeColorOfButton(gameUiState.possibleAnswers[index])),
                    enabled = gameUiState.answerSelected == null
                ) {
                    Text(text = gameUiState.possibleAnswers[index])
                }
            }
        }

    }


}