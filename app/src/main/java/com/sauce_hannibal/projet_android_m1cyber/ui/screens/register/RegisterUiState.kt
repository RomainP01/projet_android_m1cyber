package com.sauce_hannibal.projet_android_m1cyber.ui.screens.register

data class RegisterUiState(
    val email: String = "",
    val password: String = "",
    val confirmationPassword: String = "",
    val isConnected:Boolean = false
)