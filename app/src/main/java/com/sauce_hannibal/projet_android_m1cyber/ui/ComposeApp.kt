package com.sauce_hannibal.projet_android_m1cyber.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.game.GameScreen
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.leaderboard.LeaderboardScreen
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.login.LoginScreen
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.register.RegisterScreen
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.template.TemplateScreen

@Composable
fun ComposeApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.LOGIN
    ) {
        composable(Route.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Route.GAME)
                },
                onRegisterClick = {
                    navController.navigate(Route.REGISTER)
                }
            )
        }
        composable(Route.REGISTER) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Route.GAME)
                },
                onLoginClick = {
                    navController.navigate(Route.LOGIN)
                }
            )
        }
        composable(Route.GAME) {
            TemplateScreen(
                page = { GameScreen() },
                navController = navController,
                currentScreen = Route.GAME
            )
        }
        composable(Route.LEADERBOARD) {
            TemplateScreen(
                page = { LeaderboardScreen() },
                navController = navController,
                currentScreen = Route.LEADERBOARD
            )
        }


    }
}

object Route {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val GAME = "game"
    const val LEADERBOARD = "leaderboard"
    const val STREAK = "streak"
    const val HOME = "home"
}