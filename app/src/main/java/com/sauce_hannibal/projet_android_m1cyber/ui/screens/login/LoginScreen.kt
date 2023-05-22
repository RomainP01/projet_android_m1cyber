package com.sauce_hannibal.projet_android_m1cyber.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sauce_hannibal.projet_android_m1cyber.R
import com.sauce_hannibal.projet_android_m1cyber.ui.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<LoginViewModel>()
    val modifier = Modifier
    val uiState = viewModel.loginUiState.collectAsState().value
    if (uiState.isConnected) {
        navController.navigate(Route.HOME)
    }

    val emailValue = remember {
        mutableStateOf("")
    }
    val passwordValue = remember {
        mutableStateOf("")
    }
    var passwordVisibility by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White), contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.trivia_crack_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 8.dp)
                    .offset(y = 55.dp)
            )

        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.68f)
                .clip(RoundedCornerShape(30.dp))
                .background(Color.White)
                .padding(10.dp)
        ) {
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {


            }
            Text(
                text = "Sign In",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                ),
                fontSize = 38.sp,
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = emailValue.value,
                    onValueChange = { emailValue.value = it },
                    label = { Text(text = "Email Address") },
                    placeholder = { Text(text = "Email Address") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(8.8f)
                )
                OutlinedTextField(
                    value = passwordValue.value,
                    onValueChange = { passwordValue.value = it },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                            Icon(
                                painter = painterResource(id = R.drawable.password_eye),
                                contentDescription = null,
                                tint = if (passwordVisibility) Color.Black else Color.Gray
                            )
                        }
                    },
                    label = { Text(text = "Password") },
                    placeholder = { Text(text = "Password") },
                    singleLine = true,
                    visualTransformation = if (passwordVisibility) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(8.8f)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth(8.8f)
                        .height(50.dp)
                ) {
                    Text(
                        text = "Sign In",
                        fontSize = 20.sp,
                        modifier = Modifier.clickable { navController.navigate(Route.HOME) })
                }
                Spacer(modifier = Modifier.padding(20.dp))
                Text(text = "Create an account",
                    modifier = Modifier.clickable {
                        navController.navigate(Route.REGISTER)
                    })
                Spacer(modifier = Modifier.padding(20.dp))
                Text(text = "Forgot Password",
                    modifier = Modifier.clickable {
                        navController.navigate(Route.FORGOTPASSWORD)
                    })
                Spacer(modifier = Modifier.padding(20.dp))
            }

        }

    }
}


