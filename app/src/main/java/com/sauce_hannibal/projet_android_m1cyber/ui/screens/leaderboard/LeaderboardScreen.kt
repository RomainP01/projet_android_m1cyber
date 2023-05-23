package com.sauce_hannibal.projet_android_m1cyber.ui.screens.leaderboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.leaderboard.components.LeaderboardUsersListItemComponent
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.leaderboard.components.PodiumComponent
import java.util.Calendar

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
        // Titre de la page
        Text(
            text = "Leaderboard",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .wrapContentSize(Alignment.Center)

        )
        // Row contenant deux boutons changeant le mode d'affichage
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .wrapContentSize(Alignment.Center),
        ) {
            Button(
                onClick = { isAllTimeScore = true },
                // define button color
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isAllTimeScore) Color.DarkGray else Color.LightGray,
                ),
                border = BorderStroke(2.dp, Color.Black)
            ) {
                Text(
                    text = "All time",
                    color = if (isAllTimeScore) Color.LightGray else Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.width(15.dp))

            Button(
                onClick = { isAllTimeScore = false },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!isAllTimeScore) Color.DarkGray else Color.LightGray,
                ),
                border = BorderStroke(2.dp, Color.Black)

            ) {
                Text(
                    text = "Daily",
                    color = if (!isAllTimeScore) Color.LightGray else Color.DarkGray
                )
            }

        }
        // Row contenant le podium
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
                    .padding(top = 15.dp, bottom = 45.dp)
                    .wrapContentSize(Alignment.Center)
            ) {
                items(filteredUsers.size - 3) { index ->
                    val currentUser = filteredUsers[index + 3]
                    LeaderboardUsersListItemComponent(currentUser = currentUser, index = index, isAllTimeScore = isAllTimeScore)
                    LeaderboardUsersListItemComponent(currentUser = currentUser, index = index, isAllTimeScore = isAllTimeScore)
                    LeaderboardUsersListItemComponent(currentUser = currentUser, index = index, isAllTimeScore = isAllTimeScore)
                    LeaderboardUsersListItemComponent(currentUser = currentUser, index = index, isAllTimeScore = isAllTimeScore)
                }
            }

        }

    }
}



