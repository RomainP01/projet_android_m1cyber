package com.sauce_hannibal.projet_android_m1cyber.ui.screens.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sauce_hannibal.projet_android_m1cyber.service.account.AccountService
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.login.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {
    private val _registerUiState = MutableStateFlow(RegisterUiState())

    val registerUiState: StateFlow<RegisterUiState>
        get() = _registerUiState

    private val email
        get() = registerUiState.value.email

    private val password
        get() = registerUiState.value.password

    private val confirmationPassword
        get() = registerUiState.value.confirmationPassword

    fun onEmailChange(newValue: String) {
        _registerUiState.value = registerUiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        _registerUiState.value = registerUiState.value.copy(password = newValue)
    }

    fun onConformationPasswordChange(newValue: String) {
        _registerUiState.value = registerUiState.value.copy(confirmationPassword = newValue)
    }

    fun onRegisterClick(onRegisterSuccess: Unit) {
        //TODO CHECK IF PASSWORDS ARE THE SAME, CHECK IF EMAIL USED
        accountService.linkAccount(email, password)
    }
}