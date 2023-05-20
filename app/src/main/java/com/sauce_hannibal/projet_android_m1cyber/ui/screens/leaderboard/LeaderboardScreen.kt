package com.sauce_hannibal.projet_android_m1cyber.ui.screens.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LeaderboardScreen(
) {
    val viewModel = hiltViewModel<LeaderboardViewModel>()
    val modifier = Modifier
    val uiState = viewModel.leaderboardUiState.collectAsState().value
    var isAllTimeSelected by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top,
    ) {
        // Row contenant deux boutons changeant le mode d'affichage
        Row(
            Modifier
            .fillMaxWidth()
            .padding(top = 100.dp)
            .wrapContentSize(Alignment.Center),
        ) {
           Button(
                onClick = { isAllTimeSelected = true },
                modifier = Modifier
                    .background(if (isAllTimeSelected) Color.DarkGray else Color.White)
            ) {
                Text(
                    text = "All time",
                    color = if (isAllTimeSelected) Color.Black else Color.Gray

                )
            }
            Button(
                onClick = { isAllTimeSelected = false },
                modifier = Modifier
                    .background(if (!isAllTimeSelected) Color.DarkGray else Color.White)
            ) {
                Text(
                    text = "Current",
                    color = if (!isAllTimeSelected) Color.Black else Color.Gray
                )
            }

        }
        }
        // Row contenant le podium
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 200.dp)
                .wrapContentSize(Alignment.Center),
            verticalAlignment = Alignment.Bottom)
        {
            Box(
                modifier = Modifier
                    .size(85.dp)
                    .background(Color.Green)
                    .border(width = 2.dp, color = Color.Black)

            )
            Spacer(modifier = Modifier.width(5.dp))
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color.Red)
                    .border(width = 2.dp, color = Color.Black)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Box(
                modifier = Modifier
                    .size(75.dp)
                    .background(Color.Blue)
                    .border(width = 2.dp, color = Color.Black)

            )

        }
        for (user in uiState.users) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.Red),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                user.pseudo?.let {
                    Text(
                        text = it,
                        modifier = modifier
                            .fillMaxWidth(0.5f)
                            .background(Color.Green)
                    )
                }
                Text(
                    if (uiState.isAllTimeScore) user.allTimeScore.toString() else user.dailyScore.toString(),
                    modifier = modifier
                        .fillMaxWidth(0.5f)
                        .background(Color.Green)
                )
            }
        }
    }



