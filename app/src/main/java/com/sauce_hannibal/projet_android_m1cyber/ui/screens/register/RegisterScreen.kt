package com.sauce_hannibal.projet_android_m1cyber.ui.screens.register

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sauce_hannibal.projet_android_m1cyber.R
import com.sauce_hannibal.projet_android_m1cyber.ui.Route
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.Pink100

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun RegisterScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<RegisterViewModel>()
    val uiState = viewModel.registerUiState.collectAsState().value


    val passwordRegex =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$".toRegex()
    val errorMessage = remember { mutableStateOf("") }
    fun validatePassword(password: String) {
        errorMessage.value = when {
            password.length < 8 -> "The password must be at least 8 characters long."
            !passwordRegex.matches(password) ->
                "The password must contain at least one lowercase letter, one uppercase letter, one number and one special character (@#\$%^&+=)."

            else -> ""
        }
    }

    fun validateConfirmationPassword(confirmationPassword: String) {
        errorMessage.value = when {
            uiState.password != confirmationPassword -> "The password and the confirmation password must be the same."
            else -> ""
        }
    }

    LaunchedEffect(key1 = uiState, block = {
        if (uiState.isAccountCreated) {
            navController.navigate(Route.LOGIN)
        }
    })

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Pink100), contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.register_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 8.dp)
                    .offset(y = 38.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.68f)
                .clip(RoundedCornerShape(30.dp))
                .background(Color.White)
                .padding(10.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Sign Up",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                ),
                fontSize = 38.sp,
            )

            Spacer(modifier = Modifier.padding(20.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = uiState.pseudo,
                    onValueChange = {
                        viewModel.onPseudoChange(it)
                        viewModel.setPseudoErrorMessage("")
                    },
                    label = { Text(text = "Pseudo") },
                    placeholder = { Text(text = "Pseudo", color = Color.Black) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(8.8f),
                    isError = uiState.pseudoErrorMessage != "",
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black,
                    )
                )
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    value = uiState.email,
                    onValueChange = { viewModel.onEmailChange(it) },
                    label = { Text(text = "Email Address") },
                    placeholder = { Text(text = "Email Address", color = Color.Black) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(8.8f),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black,
                    )
                )
                Spacer(modifier = Modifier.padding(5.dp))

                OutlinedTextField(
                    value = uiState.password,
                    onValueChange = {
                        viewModel.onPasswordChange(it)
                        validatePassword(it)
                    },
                    trailingIcon = {
                        IconButton(onClick = { viewModel.onPasswordVisibilityChange(!uiState.passwordVisibility) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_eye),
                                contentDescription = null,
                                tint = if (uiState.passwordVisibility) Color.Black else Color.Gray
                            )
                        }
                    },
                    label = { Text(text = "Password") },
                    placeholder = { Text(text = "Password", color = Color.Black) },
                    singleLine = true,
                    visualTransformation = if (uiState.passwordVisibility) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(8.8f),
                    isError = errorMessage.value.isNotEmpty(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black,
                        focusedBorderColor = if (errorMessage.value.isNotEmpty()) Color.Red else Color.Gray,
                        unfocusedBorderColor = if (errorMessage.value.isNotEmpty()) Color.Red else Color.Gray,
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    keyboardActions = KeyboardActions(
                        onDone = {

                        }
                    )
                )

                if (errorMessage.value.isNotEmpty()) {
                    Text(
                        text = errorMessage.value,
                        color = Color.Red,
                        modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))

                OutlinedTextField(
                    value = uiState.confirmationPassword,
                    onValueChange = {
                        viewModel.onConfirmationPasswordChange(it)
                        validateConfirmationPassword(it)
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            viewModel.onPasswordVisibilityConfirmationChange(
                                !uiState.ConfirmationPasswordVisibility
                            )
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_eye),
                                contentDescription = null,
                                tint = if (uiState.ConfirmationPasswordVisibility) Color.Black else Color.Gray
                            )
                        }
                    },
                    label = { Text(text = "Confirm Password") },
                    placeholder = { Text(text = "Confirm Password", color = Color.Black) },
                    singleLine = true,
                    visualTransformation =
                    if (uiState.ConfirmationPasswordVisibility) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(8.8f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    keyboardActions = KeyboardActions(
                        onDone = {

                        }
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black,
                    )
                )



                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    onClick = { viewModel.register() },
                    modifier = Modifier
                        .fillMaxWidth(8.8f)
                        .height(50.dp)
                ) {
                    Text(text = "Sign Up", fontSize = 20.sp)

                }
                uiState.pseudoErrorMessage?.let { Text(text = it) }
                Spacer(modifier = Modifier.padding(20.dp))
                Text(text = "Login Instead",
                    modifier = Modifier.clickable {
                        navController.navigate(Route.LOGIN)
                    })
                Spacer(modifier = Modifier.padding(20.dp))
            }

        }
    }
}

