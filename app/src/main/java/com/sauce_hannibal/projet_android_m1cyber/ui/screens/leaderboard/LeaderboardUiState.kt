package com.sauce_hannibal.projet_android_m1cyber.ui.screens.leaderboard

import com.sauce_hannibal.projet_android_m1cyber.domain.LeaderBoardFirebaseItem

data class LeaderboardUiState(
    var leaderboard: List<LeaderBoardFirebaseItem> = emptyList()
)