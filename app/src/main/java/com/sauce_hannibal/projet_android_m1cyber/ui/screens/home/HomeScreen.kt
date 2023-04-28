package com.sauce_hannibal.projet_android_m1cyber.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sauce_hannibal.projet_android_m1cyber.ui.Route
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.game.GameViewModel
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.components.BottomComponent
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.components.TopComponent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val modifier = Modifier
    val gameUiState = viewModel.homeUiState.collectAsState().value
    Scaffold(
        topBar = { TopComponent() },
        bottomBar = { BottomComponent(navController = navController, currentScreen = Route.HOME) },
    ) {
        Column() {
            Button(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                onClick = {
                    navController.navigate(Route.GAME)
                }
            ) {
                Text(text = "Daily Ranked  ")
                Text("10 questions")
            }
            Button(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                onClick = {
                    navController.navigate(Route.GAME)
                }
            ) {
                Text(text = "Mode Sans Fin")
                Text(text = "1 vie")
            }
        }
    }
}