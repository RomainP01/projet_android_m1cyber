package com.sauce_hannibal.projet_android_m1cyber.ui.screens.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sauce_hannibal.projet_android_m1cyber.repository.account.AccountRepository
import com.sauce_hannibal.projet_android_m1cyber.repository.firestore.UserFirebaseRepository
import com.sauce_hannibal.projet_android_m1cyber.repository.storage.FirebaseStorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val userFirebaseRepository: UserFirebaseRepository,
    private val firebaseStorageRepository: FirebaseStorageRepository
) : ViewModel() {
    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileUiState: StateFlow<ProfileUiState>
        get() = _profileUiState


    init {
        viewModelScope.launch(Dispatchers.IO) {
            val user = accountRepository.getUserLoggedIn()
            if (user != null) {
                userFirebaseRepository.getUser(user.uid).collectLatest { userFirebase ->
                    _profileUiState.value = _profileUiState.value.copy(user = userFirebase)
                }
            }
        }
    }

    fun setNewUri(uri: Uri) {
        _profileUiState.value = _profileUiState.value.copy(newUri = uri)
    }

    fun setNewPseudo(pseudo: String) {
        _profileUiState.value = _profileUiState.value.copy(newPseudo = pseudo, errorMessage = null)
    }

    private fun updateProfilePicture(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            _profileUiState.value.user?.uid?.let {
                firebaseStorageRepository.uploadImage(uri,
                    it,
                    {},
                    {

                    })
            }
        }
    }

    fun setIsSaved(isSaved: Boolean) {
        _profileUiState.value = _profileUiState.value.copy(isSaved = isSaved)
    }

    fun saveChanges(uri: Uri?, pseudo: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            var messageShowed = false;
            if (uri != null) {
                updateProfilePicture(uri)
                setIsSaved(true)
                messageShowed = true
            }
            if (pseudo != null) {

                val isAvailable = userFirebaseRepository.isPseudoAvailable(
                    pseudo
                )
                if (isAvailable) {
                    userFirebaseRepository.updatePseudo(
                        _profileUiState.value.user?.uid!!,
                        pseudo
                    )
                    _profileUiState.value =
                        _profileUiState.value.copy(user = _profileUiState.value.user?.copy(pseudo = pseudo))
                    if (!messageShowed) setIsSaved(true)
                } else {
                    _profileUiState.value =
                        _profileUiState.value.copy(errorMessage = "Pseudo already taken")
                }
            }
            val user = accountRepository.getUserLoggedIn()
            if (user != null) {
                userFirebaseRepository.getUser(user.uid).collectLatest { userFirebase ->
                    _profileUiState.value = _profileUiState.value.copy(user = userFirebase)
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            accountRepository.logout()
            _profileUiState.value = _profileUiState.value.copy(isConnected = false)
        }
    }


}