package com.sauce_hannibal.projet_android_m1cyber.ui.screens.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sauce_hannibal.projet_android_m1cyber.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UsersUiState())
    val uiState: StateFlow<UsersUiState>
        get() = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val list = usersRepository.getUsers()
            withContext(Dispatchers.Main) {
                if (list.isEmpty()) {
                    _uiState.update { it.copy(offline = true) }
                } else {
                    _uiState.update { it.copy(offline = false, list = list) }
                }
            }
        }
    }
}