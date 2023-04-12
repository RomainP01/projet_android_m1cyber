package com.sauce_hannibal.projet_android_m1cyber.ui.screens.leaderboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sauce_hannibal.projet_android_m1cyber.repository.database.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
) : ViewModel() {
    private val _leaderboardUiState = MutableStateFlow(LeaderboardUiState())
    val leaderboardUiState: StateFlow<LeaderboardUiState>
        get() = _leaderboardUiState


    init {
        viewModelScope.launch(Dispatchers.IO) {
           databaseRepository.getLeaderboard { leaderboard ->
                _leaderboardUiState.value = _leaderboardUiState.value.copy(
                    leaderboard = leaderboard
                )
            }
        }
    }
}