package com.sauce_hannibal.projet_android_m1cyber.ui.screens.game

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sauce_hannibal.projet_android_m1cyber.R
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.game.components.PopUpComponent
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.HomeRoute
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.Blue100
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.BlueDisabled
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.Green100
import kotlinx.coroutines.delay

@Composable
fun GameScreen(
    navController: NavHostController,
    changeCurrentIndex: () -> Unit,
) {
    val viewModel = hiltViewModel<GameViewModel>()
    val gameUiState = viewModel.gameUiState.collectAsState().value

    var timeLeft by remember { mutableStateOf(gameUiState.timer) }
    LaunchedEffect(key1 = gameUiState.answerSelected, block = {
        timeLeft = gameUiState.timer
    })

    LaunchedEffect(key1 = gameUiState.isStarted, block = {
        while (!gameUiState.isStarted) {
            delay(1000)
        }
        while (!gameUiState.isEnded) {
            if (timeLeft == 0 && gameUiState.answerSelected == null) {
                delay(1000)
                viewModel.handleTimerEnd()
                timeLeft = gameUiState.timer
            } else {
                delay(1000)
                timeLeft--
            }
        }
    })

    LaunchedEffect(key1 = gameUiState.isEnded, block = {
        if (gameUiState.isEnded) {
            changeCurrentIndex()
            navController.navigate(HomeRoute.HOME)
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
        PopUpComponent(gameUiState.isOpenPopUp, navController, viewModel)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                viewModel.setIsOpenPopUp(true)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "arrow icon",
                    tint = Color.White,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Question ${gameUiState.numberOfQuestionsAnswered + 1}/${gameUiState.numberOfQuestions}",
                modifier = Modifier.align(Alignment.CenterVertically),
                color = Color.White,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Score: ${gameUiState.userScore}",
                modifier = Modifier.align(Alignment.CenterVertically),
                color = Color.White,
            )
        }
        gameUiState.currentQuestion?.let {
            Text(
                text = it.difficulty,
                modifier = Modifier
                    .padding(8.dp),
                color = viewModel.changeColorOfDifficulty(it.difficulty),
                fontSize = 26.sp,
                style = TextStyle(
                    shadow = Shadow(
                        color = Blue100,
                        offset = Offset(1f, 1f),
                        blurRadius = 5f
                    )
                )
            )
        }

        Text(
            text = "x$multiplierString",
            modifier = Modifier
                .padding(8.dp)
                .padding(4.dp),
            color = Color.White,
            fontSize = (16 + (gameUiState.multiplier * 3)).sp,
            style = TextStyle(
                shadow = Shadow(
                    color = viewModel.changeColorOfMultiplier(gameUiState.multiplier),
                    offset = Offset(1f, 1f),
                    blurRadius = 5f
                )
            )

        )
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .border(2.dp, Blue100, RoundedCornerShape(8.dp))
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(8.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                gameUiState.currentQuestion?.let {
                    Text(
                        text = it.question,
                        modifier = Modifier.padding(16.dp),
                        color = Color.Black,
                    )
                }
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier.fillMaxWidth(),
                    color = viewModel.changeColorOfTimer(progress)
                )
            }
        }

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
                        .background(
                            color = viewModel.changeColorOfButton(gameUiState.possibleAnswers[index]),
                        ),
                    enabled = gameUiState.answerSelected == null,
                    border = BorderStroke(2.dp, Color.White),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue100,
                        disabledContainerColor = BlueDisabled,
                        disabledContentColor = Color.White
                    )

                ) {
                    Text(
                        text = gameUiState.possibleAnswers[index],
                        fontSize = 20.sp,
                        color = Green100,
                        style = TextStyle(
                            shadow = Shadow(
                                color = Green100,
                                offset = Offset(1f, 1f),
                                blurRadius = 1f
                            )
                        )

                    )
                }
            }
        }

    }


}