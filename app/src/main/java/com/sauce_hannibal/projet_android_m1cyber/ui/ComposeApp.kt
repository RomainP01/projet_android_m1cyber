package com.sauce_hannibal.projet_android_m1cyber.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.details.DetailsScreen
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.login.LoginScreen
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.register.RegisterScreen
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.users.UsersScreen

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
                    navController.navigate(Route.USER)
                },
                onRegisterClick = {
                    navController.navigate(Route.REGISTER)
                }
            )
        }
        composable(Route.REGISTER) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Route.USER)
                },
                onLoginClick = {
                    navController.navigate(Route.LOGIN)
                }
            )
        }
        composable(Route.USER) { backStackEntry ->
            UsersScreen(
                onUserClick = { username ->
                    // In order to discard duplicated navigation events, we check the Lifecycle
                    if (backStackEntry.getLifecycle().currentState == Lifecycle.State.RESUMED) {
                        navController.navigate("${Route.DETAIL}/$username")
                    }
                }
            )
        }
        composable(
            route = "${Route.DETAIL}/{${Argument.USERNAME}}",
            arguments = listOf(
                navArgument(Argument.USERNAME) {
                    type = NavType.StringType
                }
            ),
        ) {
            DetailsScreen(navController)
        }
    }
}

object Route {
    const val USER = "user"
    const val DETAIL = "detail"
    const val LOGIN = "login"
    const val REGISTER = "register"
}

object Argument {
    const val USERNAME = "username"
}