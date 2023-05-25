package com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.HomeRoute
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.HomeUiState
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.HomeViewModel

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
                .padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Trivia Challenge",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = Color.White,
                fontSize = 50.sp
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
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    changeCurrentIndex()
                    navController.navigate(HomeRoute.GAME)
                },
                enabled = !homeUiState.isDailyChallengeDone
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Daily Ranked")
                    Text(text = "10 questions")
                }
            }
        }
    }


}