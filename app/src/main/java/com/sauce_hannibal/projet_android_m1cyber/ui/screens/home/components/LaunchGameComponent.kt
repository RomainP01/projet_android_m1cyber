package com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sauce_hannibal.projet_android_m1cyber.R
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.HomeRoute
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.HomeUiState
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.*

@Composable
fun LaunchGameComponent(
    navController: NavHostController,
    homeUiState: HomeUiState,
    changeCurrentIndex: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "TRIVIA CHALLENGE",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 100.dp),
                color = Color.White,
                style = MaterialTheme.typography.titleLarge.copy(
                    shadow = Shadow(
                        color = Purple100,
                        offset = Offset(1f, 1f),
                        blurRadius = 5f
                    )
                ),
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
                .align(Alignment.Center)
                .padding(top = 100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!homeUiState.isDailyChallengeDone) Color.White else Purple200
                ),
                modifier = Modifier
                    .width(250.dp)
                    .border(
                        width = 2.5.dp,
                        color = if(!homeUiState.isDailyChallengeDone) Pink100 else GreyDisabled,
                        shape = RoundedCornerShape(50.dp)
                    ),
                onClick = {
                    changeCurrentIndex()
                    navController.navigate(HomeRoute.GAME)
                },
                enabled = !homeUiState.isDailyChallengeDone
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        modifier = Modifier
                            .size(40.dp),
                        painter = if (!homeUiState.isDailyChallengeDone) painterResource(id = R.drawable.ic_arrow_play) else painterResource(
                            id = R.drawable.ic_stop
                        ),
                        contentDescription = "launch icon",
                        tint = if (!homeUiState.isDailyChallengeDone) Green100 else GreyDisabled,

                        )
                    Text(
                        text = "DAILY RANKED \n 10 QUESTIONS",
                        modifier = Modifier.weight(1f),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = if (!homeUiState.isDailyChallengeDone) Purple100 else GreyDisabled
                    )
                }

            }
        }
    }


}