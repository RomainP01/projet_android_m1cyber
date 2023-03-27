package com.mvince.compose.ui.users

import com.mvince.compose.domain.User

data class UsersUiState(
    val list: List<User> = listOf(),
    val offline: Boolean = false
)