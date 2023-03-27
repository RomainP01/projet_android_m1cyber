package com.mvince.compose.ui.details

import com.mvince.compose.domain.Details
import com.mvince.compose.util.formatDate

data class DetailsUiState(
    val detail: Details = Details(),
    val offline: Boolean = false
) {
    val formattedUserSince = formatDate(detail.userSince)
}