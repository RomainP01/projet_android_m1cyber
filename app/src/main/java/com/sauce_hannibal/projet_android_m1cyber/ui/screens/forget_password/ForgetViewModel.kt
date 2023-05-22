package com.sauce_hannibal.projet_android_m1cyber.ui.screens.forget_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sauce_hannibal.projet_android_m1cyber.repository.account.AccountRepository
import com.sauce_hannibal.projet_android_m1cyber.repository.account.FirebaseAccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetViewModel @Inject constructor(
    private val firebaseAccountRepository: FirebaseAccountRepository
) : ViewModel() {
    private val _resetPasswordFlow = MutableStateFlow<Boolean?>(null)
    val resetPasswordFlow : StateFlow<Boolean?> = _resetPasswordFlow




    fun forgotPassword(email: String) {
        viewModelScope.launch(Dispatchers.IO){
            _resetPasswordFlow.value = firebaseAccountRepository.resetPassword(email)
        }
    }

}