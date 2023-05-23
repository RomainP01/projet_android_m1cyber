package com.sauce_hannibal.projet_android_m1cyber.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sauce_hannibal.projet_android_m1cyber.R
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.game.GameScreen
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.components.LaunchGameComponent
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.leaderboard.LeaderboardScreen
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.profile.ProfileScreen
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.Purple100
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.PurplePinkBackground


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeViewModel>()
    val homeUiState = viewModel.homeUiState.collectAsState().value
    val navController = rememberNavController()
    var currentIndex by remember {
        mutableStateOf(1)
    }
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Purple100,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    )
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    ),
            ) {
                NavigationBarItem(selected = currentIndex == 0,
                    onClick = {
                        currentIndex = 0
                        navController.navigate(HomeRoute.PROFILE)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_account),
                            contentDescription = "home icon",
                            tint = Color.White
                        )
                    }
                )
                NavigationBarItem(selected = currentIndex == 1,
                    onClick = {
                        currentIndex = 1
                        navController.navigate(HomeRoute.HOME)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_home),
                            contentDescription = "home icon",
                            tint = Color.White
                        )
                    }
                )
                NavigationBarItem(selected = currentIndex == 2,
                    onClick = {
                        currentIndex = 2
                        navController.navigate(HomeRoute.LEADERBOARD)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_leaderboard),
                            contentDescription = "leaderboard icon",
                            tint = Color.White
                        )
                    }
                )
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = HomeRoute.HOME
        ) {
            composable(HomeRoute.HOME) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(PurplePinkBackground)
                ) {
                    LaunchGameComponent(navController, viewModel, homeUiState)
                }
            }
            composable(HomeRoute.PROFILE) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(PurplePinkBackground)
                ) {
                    ProfileScreen()
                }
            }
            composable(HomeRoute.LEADERBOARD) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(PurplePinkBackground)
                ) {
                    LeaderboardScreen()
                }
            }
            composable(HomeRoute.GAME) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(PurplePinkBackground)
                ) {
                    GameScreen()
                }
            }
        }
    }
}

object HomeRoute {
    const val PROFILE = "profile"
    const val LEADERBOARD = "leaderboard"
    const val GAME = "game"
    const val HOME = "home"
}