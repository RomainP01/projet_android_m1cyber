package com.sauce_hannibal.projet_android_m1cyber.ui.screens.leaderboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sauce_hannibal.projet_android_m1cyber.repository.firestore.UserFirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val userFirebaseRepository: UserFirebaseRepository
) : ViewModel() {
    private val _leaderboardUiState = MutableStateFlow(LeaderboardUiState(isAllTimeScore = true))
    val leaderboardUiState: StateFlow<LeaderboardUiState>
        get() = _leaderboardUiState


    init {
        viewModelScope.launch(Dispatchers.IO) {
            userFirebaseRepository.getAll().collect {
                _leaderboardUiState.value =
                    _leaderboardUiState.value.copy(users = it, isAllTimeScore = true)
            }
        }
    }


}