package com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sauce_hannibal.projet_android_m1cyber.ui.Route
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.HomeRoute
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.HomeUiState
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.HomeViewModel
import org.checkerframework.checker.units.qual.Current

@Composable
fun LaunchGameComponent(
    navController: NavHostController,
    viewModel: HomeViewModel,
    homeUiState: HomeUiState,
) {
    Column() {
        Button(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(viewModel.buttonBackgroundColor(homeUiState.isDailyChallengeDone)),
            onClick = {
                navController.navigate(HomeRoute.GAME)
            },
            enabled = !homeUiState.isDailyChallengeDone
        ) {
            Text(text = "Daily Ranked  ")
            Text("10 questions")
        }
    }
}