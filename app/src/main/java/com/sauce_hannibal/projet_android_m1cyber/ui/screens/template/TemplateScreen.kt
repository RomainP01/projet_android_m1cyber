package com.sauce_hannibal.projet_android_m1cyber.ui.screens.template

import android.annotation.SuppressLint
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.template.components.BottomComponent
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.template.components.TopComponent


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TemplateScreen(
    page: @Composable () -> Unit,
    navController: NavController,
    currentScreen: String
) {
    Scaffold(
        topBar = {
            TopComponent()
        },
        bottomBar = {
            BottomComponent(
               navController = navController,
               currentScreen = currentScreen
            )
        },
        content = {
            page()
        }
    )
}