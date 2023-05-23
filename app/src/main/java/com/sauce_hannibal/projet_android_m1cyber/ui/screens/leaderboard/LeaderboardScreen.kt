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
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (filteredUsers.getOrNull(1)?.profilePictureUrl.toString() != null) {
                    val imagePainter = rememberAsyncImagePainter(model = filteredUsers.getOrNull(1)?.profilePictureUrl.toString())
                    Image(
                        painter = imagePainter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(75.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Canvas(
                        modifier = Modifier
                            .size(75.dp)
                            .padding(bottom = 5.dp),
                        onDraw = { drawCircle(color = Color.LightGray) }
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(Color.LightGray)
                        .border(width = 2.dp, color = Color.Black)
                        .padding(top = 15.dp)
                ) {
                    Text(
                        text = if (isAllTimeScore) {
                            filteredUsers.getOrNull(1)?.allTimeScore.toString()
                        } else {
                            filteredUsers.getOrNull(1)?.dailyScore.toString()
                        },
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center),
                        fontWeight = FontWeight.Bold

                    )
                    Text(
                        text = filteredUsers.getOrNull(1)?.pseudo.orEmpty().take(11) ?: "",
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.TopCenter)
                    )
                    Text(
                        text = "#2nd",
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 5.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(5.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (filteredUsers.getOrNull(0)?.profilePictureUrl.toString() != null) {
                    val imagePainter = rememberAsyncImagePainter(model = filteredUsers.getOrNull(0)?.profilePictureUrl.toString())
                    Image(
                        painter = imagePainter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(90.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Canvas(
                        modifier = Modifier
                            .size(90.dp)
                            .padding(bottom = 5.dp),
                        onDraw = { drawCircle(color = Color.LightGray) }
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .background(Color.LightGray)
                        .border(width = 2.dp, color = Color.Black)
                        .padding(top = 5.dp)
                ) {
                    Text(
                        text = if (isAllTimeScore) {
                            filteredUsers.getOrNull(0)?.allTimeScore.toString()
                        } else {
                            filteredUsers.getOrNull(0)?.dailyScore.toString()
                        },
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = filteredUsers.getOrNull(0)?.pseudo.orEmpty().take(13) ?: "",
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.TopCenter)
                    )
                    Text(
                        text = "#1st",
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 5.dp)
                    )

                }
            }
            Spacer(modifier = Modifier.width(5.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (filteredUsers.getOrNull(2)?.profilePictureUrl.toString() != null) {
                    val imagePainter = rememberAsyncImagePainter(model = filteredUsers.getOrNull(2)?.profilePictureUrl.toString())
                    Image(
                        painter = imagePainter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Canvas(
                        modifier = Modifier
                            .size(70.dp)
                            .padding(bottom = 5.dp),
                        onDraw = { drawCircle(color = Color.LightGray) }
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))


                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.LightGray)
                        .border(width = 2.dp, color = Color.Black)
                        .padding(top = 5.dp)


                ) {
                    Text(
                        text = if (isAllTimeScore) {
                            filteredUsers.getOrNull(2)?.allTimeScore.toString()
                        } else {
                            filteredUsers.getOrNull(2)?.dailyScore.toString()
                        },
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.Center),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = filteredUsers.getOrNull(2)?.pseudo.orEmpty().take(9) ?: "",
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.TopCenter)
                    )
                    Text(
                        text = "#3rd",
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 5.dp)
                    )

                }
            }

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



