package com.sauce_hannibal.projet_android_m1cyber.ui.screens.register

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sauce_hannibal.projet_android_m1cyber.R
import com.sauce_hannibal.projet_android_m1cyber.domain.UserFirebase
import com.sauce_hannibal.projet_android_m1cyber.repository.account.AccountRepository
import com.sauce_hannibal.projet_android_m1cyber.repository.firestore.UserFirebaseRepository
import com.sauce_hannibal.projet_android_m1cyber.repository.storage.FirebaseStorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val userFirebaseRepository: UserFirebaseRepository,
    private val firebaseStorageRepository: FirebaseStorageRepository
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
    private val pseudo
        get() = registerUiState.value.pseudo

    fun onEmailChange(newValue: String) {
        _registerUiState.value = registerUiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        _registerUiState.value = registerUiState.value.copy(password = newValue)
    }

    fun onConfirmationPasswordChange(newValue: String) {
        _registerUiState.value = registerUiState.value.copy(confirmationPassword = newValue)
    }

    fun onPasswordVisibilityChange(newValue: Boolean) {
        _registerUiState.value = _registerUiState.value.copy(passwordVisibility = newValue)
    }

    fun onPasswordVisibilityConfirmationChange(newValue: Boolean) {
        _registerUiState.value =
            _registerUiState.value.copy(ConfirmationPasswordVisibility = newValue)
    }

    fun onPseudoChange(newValue: String) {
        _registerUiState.value = _registerUiState.value.copy(pseudo = newValue)
    }

    fun setPseudoErrorMessage(newValue: String) {
        _registerUiState.value = _registerUiState.value.copy(pseudoErrorMessage = newValue)
    }


    fun register() {
        viewModelScope.launch(Dispatchers.IO) {
            if (userFirebaseRepository.isPseudoAvailable(pseudo)) {
                val uid = accountRepository.signUp(email, password)?.uid
                if (uid != null) {
                    val defaultProfilePictures = arrayOf(
                        "default_profile_picture", "default_profile_picture2"
                    )
                    val randomIndex = (defaultProfilePictures.indices).random()
                    val resourceName = defaultProfilePictures[randomIndex]
                    val defaultProfilePictureUri = Uri.parse(
                        "android.resource://com.sauce_hannibal.projet_android_m1cyber/drawable/$resourceName"
                    )
                    firebaseStorageRepository.uploadImage(defaultProfilePictureUri,
                        uid,
                        successCallback = { downloadUrl ->
                            val user = UserFirebase(
                                uid = uid, pseudo = pseudo, profilePictureUrl = downloadUrl
                            )
                            userFirebaseRepository.insertUser(uid, user)
                        },
                        errorCallback = { exception ->
                            throw exception
                        })
                    _registerUiState.value = _registerUiState.value.copy(isAccountCreated = true)

                }
            } else {
                _registerUiState.value = _registerUiState.value.copy(
                    pseudoErrorMessage = "Pseudo already taken"
                )
            }
        }
    }
}