package com.sauce_hannibal.projet_android_m1cyber.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sauce_hannibal.projet_android_m1cyber.ui.Route
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.components.BottomComponent
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.components.TopComponent
import kotlinx.coroutines.Delay
import java.util.Timer


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val gameUiState = viewModel.homeUiState.collectAsState().value
    Scaffold() {
        Column() {
            Button(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .background(viewModel.buttonBackgroundColor(gameUiState.isDailyChallengeDone)),
                onClick = {
                    navController.navigate(Route.GAME)
                },
                enabled = !gameUiState.isDailyChallengeDone
            ) {
                Text(text = "Daily Ranked  ")
                Text("10 questions")
            }
        }
    }
}