package com.sauce_hannibal.projet_android_m1cyber.ui.screens.home

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sauce_hannibal.projet_android_m1cyber.repository.account.AccountRepository
import com.sauce_hannibal.projet_android_m1cyber.repository.firestore.UserFirebaseRepository
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.Purple200
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val userFirebaseRepository: UserFirebaseRepository
) : ViewModel() {
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState>
        get() = _homeUiState

    init {
        viewModelScope.launch {
            //wait for user to be logged in
            val user = accountRepository.getUserLoggedIn()
            isDailyChallengeDone()
            if (user != null) {
                val userFirebase = userFirebaseRepository.getUser(user.uid)
                _homeUiState.value = _homeUiState.value.copy(currentUser = userFirebase)
            }
        }

    }

    private suspend fun isDailyChallengeDone() {
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }.time

        var isDone = false
        _homeUiState.value.currentUser?.collectLatest {
            it.lastTimeDailyAnswered?.let { lastTimeDailyAnswered ->
                isDone = lastTimeDailyAnswered == today
            }
        }
        _homeUiState.value = _homeUiState.value.copy(isDailyChallengeDone = isDone)
    }

    fun buttonBackgroundColor(isDailyChallengeDone: Boolean): Color {
        if (isDailyChallengeDone) {
            return Color.Gray
        }
        return Purple200
    }


}