package com.sauce_hannibal.projet_android_m1cyber.ui.screens.home

import com.sauce_hannibal.projet_android_m1cyber.domain.UserFirebase
import com.sauce_hannibal.projet_android_m1cyber.ui.Route
import kotlinx.coroutines.flow.Flow

data class HomeUiState(
    val currentScreen: String = Route.HOME,
    val currentUser: Flow<UserFirebase>? = null,
)