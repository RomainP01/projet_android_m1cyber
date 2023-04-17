package com.sauce_hannibal.projet_android_m1cyber.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sauce_hannibal.projet_android_m1cyber.ui.Route

@Composable
fun LoginScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<LoginViewModel>()
    val modifier = Modifier
    val uiState = viewModel.loginUiState.collectAsState().value
    if (uiState.isConnected) {
        navController.navigate(Route.GAME)
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicTextField(
            uiState.email,
            viewModel::onEmailChange,
            modifier = modifier.background(color = Color.Cyan)
        )
        BasicTextField(
            uiState.password,
            viewModel::onPasswordChange,
            modifier = modifier.background(color = Color.LightGray)
        )
        Button(onClick = {
            viewModel.login(
                uiState.email, uiState.password
            )
        }) {
            Text("Login")
        }
        Button(onClick = { navController.navigate(Route.REGISTER) }) {
            Text(text = "Register")
        }
    }
}


