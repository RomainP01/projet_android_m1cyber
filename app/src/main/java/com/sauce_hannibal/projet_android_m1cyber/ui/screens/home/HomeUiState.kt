package com.sauce_hannibal.projet_android_m1cyber.ui.screens.home

import com.sauce_hannibal.projet_android_m1cyber.domain.UserFirebase
import com.sauce_hannibal.projet_android_m1cyber.ui.Route

data class HomeUiState(
    val currentScreen: String = Route.HOME,
    val currentUser: UserFirebase? = null,
    val isDailyChallengeDone: Boolean = false,
)