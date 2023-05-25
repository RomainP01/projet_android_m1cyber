package com.sauce_hannibal.projet_android_m1cyber.ui.screens.game.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.game.GameViewModel

@Composable
fun PopUpComponent(isOpenPopUp: Boolean, navController: NavController, viewModel: GameViewModel) {
    if (isOpenPopUp) {
        AlertDialog(onDismissRequest = {},
            title = {
                Text(text = "Alert")
            },
            text = {
                Text(text = "Do you really want to quit this game?")
            },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.setIsOpenPopUp(!isOpenPopUp)
                }) {
                    Text(text = "Cancel")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    viewModel.handleEndOfGame()
                    navController.popBackStack()
                }) {
                    Text(text = "Confirm")
                }
            }
        )
    }
}


