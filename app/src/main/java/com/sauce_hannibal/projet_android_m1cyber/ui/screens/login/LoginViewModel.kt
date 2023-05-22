package com.sauce_hannibal.projet_android_m1cyber.ui.screens.login

import androidx.lifecycle.ViewModel
import com.sauce_hannibal.projet_android_m1cyber.repository.account.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountRepository: AccountRepository
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
    fun onPasswordVisibilityChange(newValue: Boolean){
        _loginUiState.value = _loginUiState.value.copy(passwordVisibility = newValue)
    }

    fun login(email: String, password: String) {
        val isLoggingSuccessful = accountRepository.login(email, password)
       if ( isLoggingSuccessful){
           _loginUiState.value = _loginUiState.value.copy(isConnected = true)
       }else{
           _loginUiState.value = _loginUiState.value.copy(errorMessage = "Email or password incorrect")
       }

    }

}