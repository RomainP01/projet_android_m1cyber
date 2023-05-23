package com.sauce_hannibal.projet_android_m1cyber.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sauce_hannibal.projet_android_m1cyber.R
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.game.GameScreen
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.components.LaunchGameComponent
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.leaderboard.LeaderboardScreen
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.profile.ProfileScreen


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
    var isBottomBarHidden by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(
        bottomBar = {
            if(!isBottomBarHidden) {
                NavigationBar {
                    NavigationBarItem(selected = currentIndex == 0,
                        onClick = {
                            currentIndex = 0
                            navController.navigate(HomeRoute.PROFILE)
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_account),
                                contentDescription = "home icon"
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
                                contentDescription = "home icon"
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
                                contentDescription = "leaderboard icon"
                            )
                        }
                    )
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = HomeRoute.HOME
        ) {
            composable(HomeRoute.HOME) {
                isBottomBarHidden = false
                LaunchGameComponent(navController, viewModel, homeUiState)
            }
            composable(HomeRoute.PROFILE) {
                ProfileScreen()
            }
            composable(HomeRoute.LEADERBOARD) {
                LeaderboardScreen()
            }
            composable(HomeRoute.GAME) {
                isBottomBarHidden = true
                GameScreen(navController)
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