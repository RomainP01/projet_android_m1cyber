package com.sauce_hannibal.projet_android_m1cyber.ui.screens.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun LeaderboardScreen() {
    val viewModel = hiltViewModel<LeaderboardViewModel>()
    val uiState = viewModel.leaderboardUiState.collectAsState().value
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (user in uiState.users) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                user.pseudo?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .background(Color.Green)
                    )
                }
                Text(
                    if (uiState.isAllTimeScore) user.allTimeScore.toString() else user.dailyScore.toString(),
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .background(Color.Green)
                )
            }
        }
    }
}


