package com.sauce_hannibal.projet_android_m1cyber.ui.screens.leaderboard

import com.sauce_hannibal.projet_android_m1cyber.domain.UserFirebase

data class LeaderboardUiState(
    var users: List<UserFirebase> = emptyList(),
    var isAllTimeScore: Boolean = true
)