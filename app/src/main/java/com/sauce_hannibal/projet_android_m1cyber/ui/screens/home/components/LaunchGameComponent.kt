package com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.HomeRoute
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.HomeUiState
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.HomeViewModel
import com.sauce_hannibal.projet_android_m1cyber.R
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.Green100
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.Purple100

@Composable
fun LaunchGameComponent(
    navController: NavHostController,
    viewModel: HomeViewModel,
    homeUiState: HomeUiState,
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
                        blurRadius = 5f                    )
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
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    navController.navigate(HomeRoute.GAME)
                },
                enabled = !homeUiState.isDailyChallengeDone
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_play_arrow_24),
                        contentDescription = "launch icon",
                        tint = Color.White
                    )
                    Text(
                        text = "DAILY RANKED \n 10 QUESTIONS",
                        modifier = Modifier.weight(1f),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }

            }
        }
    }


}