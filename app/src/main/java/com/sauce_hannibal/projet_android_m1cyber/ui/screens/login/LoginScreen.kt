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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.sauce_hannibal.projet_android_m1cyber.ui.Route

@Composable
fun LoginScreen(
    onLoginSuccess: (String) -> Unit,
    onRegisterClick: (String) -> Unit,
) {
    val viewModel = hiltViewModel<LoginViewModel>()
    val modifier = Modifier
    val uiState = viewModel.loginUiState.collectAsState().value
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
        Button(onClick = { viewModel.onLoginClick(
            onLoginSuccess(Route.GAME)
        ) }) {
            Text("Login")
        }
        Button(onClick = { onRegisterClick(Route.REGISTER) }) {
            Text(text = "Register")
        }
    }
}


