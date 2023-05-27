package com.sauce_hannibal.projet_android_m1cyber.ui.screens.home

import com.sauce_hannibal.projet_android_m1cyber.domain.UserFirebase

data class HomeUiState(
    val currentUser: UserFirebase? = null,
    val isDailyChallengeDone: Boolean = false,
)