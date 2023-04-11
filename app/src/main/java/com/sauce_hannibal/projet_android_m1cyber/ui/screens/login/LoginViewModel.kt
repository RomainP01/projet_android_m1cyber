package com.sauce_hannibal.projet_android_m1cyber.ui.screens.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sauce_hannibal.projet_android_m1cyber.service.account.AccountService
import com.sauce_hannibal.projet_android_m1cyber.ui.screens.game.GameUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {
    private val _loginUiState = MutableStateFlow(LoginUiState())

    val loginUiState: StateFlow<LoginUiState>
        get() = _loginUiState


    fun onEmailChange(newValue: String) {
        _loginUiState.value = _loginUiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        _loginUiState.value = _loginUiState.value.copy(password = newValue)
    }

    fun onLoginClick(onLoginSuccess: Unit) {
        val email = loginUiState.value.email
        val password = loginUiState.value.password
        //TODO check if email is valid and password is not empty
        accountService.login(email, password)
        return onLoginSuccess
    }

}