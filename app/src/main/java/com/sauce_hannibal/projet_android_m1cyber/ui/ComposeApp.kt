package com.sauce_hannibal.projet_android_m1cyber.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.forget_password.ForgotScreen
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.game.GameScreen
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.HomeScreen
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.leaderboard.LeaderboardScreen
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.login.LoginScreen
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.profile.ProfileScreen
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.register.RegisterScreen

@Composable
fun ComposeApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.LOGIN
    ) {
        composable(Route.LOGIN) {
            LoginScreen(
                navController
            )
        }
        composable(Route.REGISTER) {
            RegisterScreen(
                navController
            )
        }
        composable(Route.FORGOTPASSWORD) { ForgotScreen(navController) }
        composable(Route.HOME) { HomeScreen() }
    }
}

object Route {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val FORGOTPASSWORD = "forgotpassword"
}