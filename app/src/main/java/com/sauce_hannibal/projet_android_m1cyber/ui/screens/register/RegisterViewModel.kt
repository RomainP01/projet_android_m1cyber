package com.sauce_hannibal.projet_android_m1cyber.ui.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sauce_hannibal.projet_android_m1cyber.domain.UserFirebase
import com.sauce_hannibal.projet_android_m1cyber.repository.account.AccountRepository
import com.sauce_hannibal.projet_android_m1cyber.repository.firestore.UserFirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val userFirebaseRepository: UserFirebaseRepository
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

    fun register() {
        viewModelScope.launch(Dispatchers.IO) {
            val uid = accountRepository.signUp(email,password)?.uid
            if (uid != null) {
               _registerUiState.value.isConnected = userFirebaseRepository.insertUser(uid, UserFirebase(uid,"Romain" ) )
            }
        }
    }
}