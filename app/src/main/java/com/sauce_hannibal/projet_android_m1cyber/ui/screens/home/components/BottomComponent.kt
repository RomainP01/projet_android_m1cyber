package com.sauce_hannibal.projet_android_m1cyber.ui.screens.home.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.sauce_hannibal.projet_android_m1cyber.R
import com.sauce_hannibal.projet_android_m1cyber.ui.Route

@Composable
fun BottomComponent(navController: NavController, currentScreen: String) {
    NavigationBar {
        NavigationBarItem(selected = Route.STREAK === currentScreen,
            onClick = { navController.navigate(Route.STREAK) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_fire),
                    contentDescription = "streak icon"
                )
            }
        )
        NavigationBarItem(selected = Route.HOME === currentScreen,
            onClick = { navController.navigate(Route.HOME) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_home),
                    contentDescription = "home icon"
                )
            }
        )
        NavigationBarItem(selected = Route.LEADERBOARD === currentScreen,
            onClick = { navController.navigate(Route.LEADERBOARD) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_leaderboard),
                    contentDescription = "leaderboard icon"
                )
            }
        )
    }
}