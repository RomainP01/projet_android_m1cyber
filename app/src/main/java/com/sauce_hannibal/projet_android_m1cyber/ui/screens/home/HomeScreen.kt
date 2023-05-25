package com.sauce_hannibal.projet_android_m1cyber.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.sauce_hannibal.projet_android_m1cyber.R
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.game.GameScreen
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.components.LaunchGameComponent
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.leaderboard.LeaderboardScreen
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.profile.ProfileScreen
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.Green100
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.Purple100
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.PurplePinkBackground


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(navControllerMain: NavController) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val homeUiState = viewModel.homeUiState.collectAsState().value
    val navController = rememberNavController()
    var currentIndex by remember {
        mutableStateOf(1)
    }
    val navColors = NavigationBarItemDefaults.colors(
        selectedIconColor = Green100,
        unselectedIconColor = Color.White,
    )

    Scaffold(
        bottomBar = {
            if (currentIndex != 4) {
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
                    NavigationBarItem(
                        selected = currentIndex == 0,
                        onClick = {
                            currentIndex = 0
                            navController.navigate(HomeRoute.PROFILE)
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_account),
                                contentDescription = "home icon",
                            )
                        },
                        colors = navColors,
                        label = {
                            Text(text = "Profile", color = Color.White)
                        }
                    )
                    NavigationBarItem(
                        selected = currentIndex == 1,
                        onClick = {
                            currentIndex = 1
                            navController.navigate(HomeRoute.HOME)
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_home),
                                contentDescription = "home icon",
                            )
                        },
                        colors = navColors,
                        label = {
                            Text(text = "Home", color = Color.White)
                        }
                    )
                    NavigationBarItem(
                        selected = currentIndex == 2,
                        onClick = {
                            currentIndex = 2
                            navController.navigate(HomeRoute.LEADERBOARD)
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_leaderboard),
                                contentDescription = "leaderboard icon",
                            )
                        },
                        colors = navColors,
                        label = {
                            Text(text = "Leaderboard", color = Color.White)
                        },
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
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(PurplePinkBackground)
                ) {
                    LaunchGameComponent(navController, homeUiState) {
                        currentIndex = 4
                    }
                }
            }
            composable(HomeRoute.PROFILE) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(PurplePinkBackground)
                ) {
                    ProfileScreen(navControllerMain)
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
                    GameScreen(navController) {
                        currentIndex = 1
                    }
                }
                GameScreen(navController) {
                    currentIndex = 1
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