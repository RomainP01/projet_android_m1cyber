package com.sauce_hannibal.projet_android_m1cyber.ui.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun RegisterScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<RegisterViewModel>()
    val modifier = Modifier
    val uiState = viewModel.registerUiState.collectAsState().value

    val nameValue = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val confirmPasswordValue = remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var confirmpasswordVisibility by remember { mutableStateOf(false) }

    val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$".toRegex()
    val errorMessage = remember { mutableStateOf("") }
    fun validatePassword(password: String) {
        errorMessage.value = when {
            password.length < 8 -> "Le mot de passe doit contenir au moins 8 caractères."
            !passwordRegex.matches(password) ->
                "Le mot de passe doit contenir au moins une lettre minuscule, une lettre majuscule, un chiffre, " +
                        "un caractère spécial (@#\$%^&+=) et aucun espace."

            else -> ""
        }
    }



    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.TopCenter){
            Image(
                painter = painterResource(id = R.drawable.register_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 8.dp).offset(y = 38.dp)
            )        }

        Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.68f)
                .clip(RoundedCornerShape(30.dp))
                .background(Color.White)
                .padding(10.dp).verticalScroll(rememberScrollState()),
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
                        value = nameValue.value,
                        onValueChange = { nameValue.value = it },
                        label = { Text(text = "Pseudo") },
                        placeholder = { Text(text = "Pseudo") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(8.8f)
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    OutlinedTextField(
                        value = emailValue.value,
                        onValueChange = { emailValue.value = it },
                        label = { Text(text = "Email Address") },
                        placeholder = { Text(text = "Email Address") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(8.8f)
                    )
                    Spacer(modifier = Modifier.padding(5.dp))

                    OutlinedTextField(
                        value = passwordValue.value,
                        onValueChange = {
                            passwordValue.value = it
                            validatePassword(it)
                        },
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
                        modifier = Modifier.fillMaxWidth(8.8f),
                        isError = errorMessage.value.isNotEmpty(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
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
                        value = confirmPasswordValue.value,
                        onValueChange = { confirmPasswordValue.value = it },
                        trailingIcon = {
                            IconButton(onClick = { confirmpasswordVisibility = !confirmpasswordVisibility }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.password_eye),
                                    contentDescription = null,
                                    tint = if (confirmpasswordVisibility) Color.Black else Color.Gray
                                )
                            }
                        },
                        label = { Text(text = "Confirm Password") },
                        placeholder = { Text(text = "Confirm Password") },
                        singleLine = true,
                        visualTransformation = if (confirmpasswordVisibility) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(8.8f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        keyboardActions = KeyboardActions(
                            onDone = {

                            }
                        )
                    )


                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(onClick = {},
                        modifier = Modifier
                            .fillMaxWidth(8.8f)
                            .height(50.dp)
                    ){
                        Text(text = "Sign Up", fontSize = 20.sp)
                    }
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
