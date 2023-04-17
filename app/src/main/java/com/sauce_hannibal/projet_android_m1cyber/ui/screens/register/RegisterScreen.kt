package com.sauce_hannibal.projet_android_m1cyber.ui.screens.register

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
fun RegisterScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<RegisterViewModel>()
    val modifier = Modifier
    val uiState = viewModel.registerUiState.collectAsState().value
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
        BasicTextField(
            uiState.confirmationPassword,
            viewModel::onConformationPasswordChange,
            modifier = modifier.background(color = Color.Red)
        )
        Button(onClick = {
            viewModel.register()
        }) {
            Text("Create account")
        }
        Button(onClick = { navController.navigate(Route.LOGIN) }) {
            Text(text = "Already have an account? Login here")
        }
    }
}