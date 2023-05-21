package com.sauce_hannibal.projet_android_m1cyber.ui.screens.leaderboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LeaderboardScreen() {
    val viewModel = hiltViewModel<LeaderboardViewModel>()
    val modifier = Modifier
    val uiState = viewModel.leaderboardUiState.collectAsState().value
    var isAllTimeScore by remember { mutableStateOf(uiState.isAllTimeScore) }

    val filteredUsers = if (isAllTimeScore) {
        uiState.users
            .filter { it.allTimeScore != null }
            .sortedByDescending { it.allTimeScore }
    } else {
        uiState.users
            .filter { it.dailyScore != null }
            .sortedByDescending { it.dailyScore }
    }



    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top,
    ) {
        // Titre de la page
        Text(text = "Leaderboard",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .wrapContentSize(Alignment.Center)

        )
        // Row contenant deux boutons changeant le mode d'affichage
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 70.dp)
                .wrapContentSize(Alignment.Center),
        ) {
            Button(
                onClick = { isAllTimeScore = true },
                modifier = Modifier
                    .background(if (isAllTimeScore) Color.DarkGray else Color.White)
            ) {
                Text(
                    text = "All time",
                    color = if (isAllTimeScore) Color.Black else Color.DarkGray
                )
            }
            Button(
                onClick = { isAllTimeScore = false },
                modifier = Modifier
                    .background(if (!isAllTimeScore) Color.DarkGray else Color.White)
            ) {
                Text(
                    text = "Daily",
                    color = if (!isAllTimeScore) Color.Black else Color.DarkGray
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
        verticalAlignment = Alignment.Bottom
    )
    {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Canvas(
                modifier = Modifier
                    .size(80.dp)
                    .padding(bottom = 5.dp),
                onDraw = { drawCircle(color = Color.LightGray) }
            )
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color.LightGray)
                    .border(width = 2.dp, color = Color.Black)
            ) {                 Text(
                text = if (isAllTimeScore) {
                    filteredUsers.getOrNull(1)?.allTimeScore.toString()
                } else {
                    filteredUsers.getOrNull(1)?.dailyScore.toString()
                },
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)

            )
            }
        }

        Spacer(modifier = Modifier.width(5.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Canvas(
                modifier = Modifier
                    .size(90.dp)
                    .padding(bottom = 5.dp),
                onDraw = { drawCircle(color = Color.LightGray) }
            )

            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.LightGray)
                    .border(width = 2.dp, color = Color.Black)
            ) {
                Text(
                    text = if (isAllTimeScore) {
                        filteredUsers.getOrNull(0)?.allTimeScore.toString()
                    } else {
                        filteredUsers.getOrNull(0)?.dailyScore.toString()
                    },
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)

                )
            }
        }
        Spacer(modifier = Modifier.width(5.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Canvas(
                modifier = Modifier
                    .size(70.dp)
                    .padding(bottom = 5.dp),
                onDraw = { drawCircle(color = Color.LightGray) }
            )

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.LightGray)
                    .border(width = 2.dp, color = Color.Black)

            ) {
                Text(
                    text = if (isAllTimeScore) {
                        filteredUsers.getOrNull(2)?.allTimeScore.toString()
                    } else {
                        filteredUsers.getOrNull(2)?.dailyScore.toString()
                    },
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)

                )

            }
        }

    }
    if (filteredUsers.size > 3) {
        filteredUsers.subList(3, filteredUsers.size).forEachIndexed { index, user ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Canvas(
                    modifier = Modifier
                        .size(30.dp)
                        .padding(bottom = 5.dp),
                    onDraw = { drawCircle(color = Color.LightGray) }
                )

                Text(
                    text = user.pseudo ?: "",
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.Black
                )

                Text(
                    text = if (isAllTimeScore) {
                        user.allTimeScore?.toString() ?: ""
                    } else {
                        user.dailyScore?.toString() ?: ""
                    },
                    modifier = Modifier
                        .padding(start = 8.dp, end = 16.dp)
                        .weight(1f)
                        .wrapContentWidth(Alignment.End),
                    color = Color.Black,
                    textAlign = TextAlign.End
                )
            }
        }
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



