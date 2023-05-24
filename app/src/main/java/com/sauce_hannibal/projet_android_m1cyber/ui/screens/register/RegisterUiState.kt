package com.sauce_hannibal.projet_android_m1cyber.ui.screens.register

data class RegisterUiState(
    var email: String = "",
    var password: String = "",
    var confirmationPassword: String = "",
    var isAccountCreated:Boolean = false,
    var passwordVisibility: Boolean = false,
    var ConfirmationPasswordVisibility: Boolean = false,
    var errorMessage: String? = null,
    var pseudo : String = ""
)