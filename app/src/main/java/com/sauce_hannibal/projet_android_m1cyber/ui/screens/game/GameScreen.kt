package com.sauce_hannibal.projet_android_m1cyber.ui.screens.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.game.components.GameTimerComponent

@Composable
fun GameScreen() {
    val viewModel = hiltViewModel<GameViewModel>()
    val modifier = Modifier
    val gameUiState = viewModel.gameUiState.collectAsState().value

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        gameUiState.currentQuestion?.let {
            Text(
                text = it.question,
                modifier = modifier.padding(16.dp)
            )
        }
        GameTimerComponent(gameUiState = gameUiState, modifier = modifier)
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            items(gameUiState.possibleAnswers.size) { index ->
                Button(
                    onClick = {
                        viewModel.onAnswerClick(gameUiState.possibleAnswers[index])
                    },
                    modifier = modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .background(color = viewModel.changeColorOfButton(gameUiState.possibleAnswers[index]))
                ) {
                    Text(text = gameUiState.possibleAnswers[index])
                }
            }
        }

    }


}