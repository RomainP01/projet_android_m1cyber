package com.sauce_hannibal.projet_android_m1cyber.ui.screens.leaderboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.leaderboard.components.LeaderboardUsersListItemComponent
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.leaderboard.components.PodiumComponent
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.GreyDisabled
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.Pink100
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.Purple100
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.Purple200
import java.util.*

@Composable
fun LeaderboardScreen() {
    val viewModel = hiltViewModel<LeaderboardViewModel>()
    val modifier = Modifier
    val uiState = viewModel.leaderboardUiState.collectAsState().value
    var isAllTimeScore by remember { mutableStateOf(uiState.isAllTimeScore) }
    val today = Calendar.getInstance()
    today.set(Calendar.HOUR_OF_DAY, 0)
    today.set(Calendar.MINUTE, 0)
    today.set(Calendar.SECOND, 0)
    today.set(Calendar.MILLISECOND, 0)
    val todayDateObject = today.time
    val filteredUsers = if (isAllTimeScore) {
        uiState.users
            .filter { it.allTimeScore != null }
            .sortedByDescending { it.allTimeScore }
    } else {
        uiState.users
            .filter {
                    it.dailyScore != null
                            && it.lastTimeDailyAnswered?.after(todayDateObject) ?: false
            }
            .sortedByDescending { it.dailyScore }
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            text = "Leaderboard",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .wrapContentSize(Alignment.Center),
            color = Color.White,
            style = MaterialTheme.typography.titleMedium.copy(
                shadow = Shadow(
                    color = Pink100,
                    offset = Offset(1f, 1f),
                    blurRadius = 1f
                )
            )
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .wrapContentSize(Alignment.Center),
        ) {
            Button(
                onClick = { isAllTimeScore = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isAllTimeScore) Purple100 else Purple200,
                ),
                border = BorderStroke(2.dp, Color.Black)
            ) {
                Text(
                    text = "ALL TIME",
                    color = if (isAllTimeScore) Color.White else GreyDisabled,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.width(15.dp))

            Button(
                onClick = { isAllTimeScore = false },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!isAllTimeScore) Purple100 else Purple200,
                ),
                border = BorderStroke(2.dp, Color.Black)

            ) {
                Text(
                    text = "DAILY",
                    color = if (!isAllTimeScore) Color.White else GreyDisabled,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .wrapContentSize(Alignment.Center),
            verticalAlignment = Alignment.Bottom
        )
        {
            PodiumComponent(filteredUsers = filteredUsers, isAllTimeScore = isAllTimeScore)
        }
        if (filteredUsers.size > 3) {
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, bottom = 90.dp)
                    .wrapContentSize(Alignment.Center)
            ) {
                items(filteredUsers.size - 3) { index ->
                    val currentUser = filteredUsers[index + 3]
                    LeaderboardUsersListItemComponent(currentUser = currentUser, index = index, isAllTimeScore = isAllTimeScore)
                    LeaderboardUsersListItemComponent(currentUser = currentUser, index = index, isAllTimeScore = isAllTimeScore)
                    LeaderboardUsersListItemComponent(currentUser = currentUser, index = index, isAllTimeScore = isAllTimeScore)
                    LeaderboardUsersListItemComponent(currentUser = currentUser, index = index, isAllTimeScore = isAllTimeScore)
                    LeaderboardUsersListItemComponent(currentUser = currentUser, index = index, isAllTimeScore = isAllTimeScore)
                }
            }

        }

    }
}



