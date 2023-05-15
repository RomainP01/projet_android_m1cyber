package com.sauce_hannibal.projet_android_m1cyber.ui.screens.home

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sauce_hannibal.projet_android_m1cyber.repository.account.AccountRepository
import com.sauce_hannibal.projet_android_m1cyber.repository.firestore.UserFirebaseRepository
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.Purple200
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
        viewModelScope.launch(Dispatchers.IO) {
            val user = accountRepository.getUserLoggedIn()
            if (user != null) {
                userFirebaseRepository.getUser(user.uid).collectLatest { userFirebase ->
                    _homeUiState.value = _homeUiState.value.copy(currentUser = userFirebase)
                    setIsDailyChallengeDone()
                }
            }
        }
    }


    private fun setIsDailyChallengeDone() {
        val today = Calendar.getInstance()
        val currentUser = _homeUiState.value.currentUser
        if (currentUser != null) {
            val lastTimeDailyAnswered = currentUser.lastTimeDailyAnswered
            if (lastTimeDailyAnswered != null) {
                val lastAnsweredCalendar =
                    Calendar.getInstance().apply { time = lastTimeDailyAnswered }
                if (lastAnsweredCalendar.get(Calendar.YEAR) == today.get(Calendar.YEAR)
                    && lastAnsweredCalendar.get(Calendar.MONTH) == today.get(Calendar.MONTH)
                    && lastAnsweredCalendar.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH)
                ) {
                    _homeUiState.value = _homeUiState.value.copy(isDailyChallengeDone = true)
                } else {
                    _homeUiState.value = _homeUiState.value.copy(isDailyChallengeDone = false)
                }
            }
        }
    }


    fun buttonBackgroundColor(isDailyChallengeDone: Boolean): Color {
        if (isDailyChallengeDone) {
            return Color.Gray
        }
        return Purple200
    }


}