package com.sauce_hannibal.projet_android_m1cyber.ui.users

import com.sauce_hannibal.projet_android_m1cyber.domain.User

data class UsersUiState(
    val list: List<User> = listOf(),
    val offline: Boolean = false
)