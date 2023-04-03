package com.sauce_hannibal.projet_android_m1cyber.ui.screens.details

import com.sauce_hannibal.projet_android_m1cyber.domain.Details
import com.sauce_hannibal.projet_android_m1cyber.util.formatDate

data class DetailsUiState(
    val detail: Details = Details(),
    val offline: Boolean = false
) {
    val formattedUserSince = formatDate(detail.userSince)
}