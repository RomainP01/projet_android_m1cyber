package com.sauce_hannibal.projet_android_m1cyber.ui.screens.forget_password

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sauce_hannibal.projet_android_m1cyber.R
import com.sauce_hannibal.projet_android_m1cyber.ui.Route
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.TextButton



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<ForgetViewModel>()
    val modifier = Modifier
    val uiState = viewModel.resetPasswordFlow.collectAsState().value
    if (uiState == true) {
        navController.navigate(Route.LOGIN)
    }




    var emailValue by remember {
        mutableStateOf("")
    }
    var showDialog by remember { mutableStateOf(false) }




    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = modifier
                .fillMaxSize()

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
                text = "Forgot Password",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                ),
                fontSize = 38.sp,
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = emailValue,
                    onValueChange = { emailValue = it },
                    label = { Text(text = "Email Address") },
                    placeholder = { Text(text = "Email Address") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(8.8f)
                )

                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    onClick = {
                        viewModel.forgotPassword(emailValue.trim())
                        showDialog = true
                    },
                    modifier = Modifier
                        .fillMaxWidth(8.8f)
                        .height(50.dp)
                ) {
                    Text(
                        text = "Send",
                        fontSize = 20.sp,
                        modifier = Modifier.clickable { }
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))
                Text(text = "Login Instead",
                    modifier = Modifier.clickable {
                        navController.navigate(Route.LOGIN)
                    })
                Spacer(modifier = Modifier.padding(20.dp))
                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = {
                            showDialog = false // Fermer la boîte de dialogue lorsque l'utilisateur la ferme
                        },
                        title = {
                            Text(text = "Mail envoyé")
                        },
                        text = {
                            Text(text = "Le mail a été envoyé avec succès.")
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    showDialog = false // Fermer la boîte de dialogue lorsque l'utilisateur clique sur le bouton OK
                                }
                            ) {
                                Text(text = "OK")
                            }
                        }
                    )
                }

            }



            }

        }

    }



