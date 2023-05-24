package com.sauce_hannibal.projet_android_m1cyber.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.PurplePinkBackground

@Composable
fun ComposeApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.HOME
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
        composable(Route.FORGOTPASSWORD) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(PurplePinkBackground)
            ) { ForgotScreen(navController) }
        }
        composable(Route.HOME) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(PurplePinkBackground)
            ) { HomeScreen() }
        }
    }
}

object Route {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val FORGOTPASSWORD = "forgotpassword"
}