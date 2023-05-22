package com.sauce_hannibal.projet_android_m1cyber.ui.screens.profile

import android.net.Uri
import com.sauce_hannibal.projet_android_m1cyber.domain.UserFirebase

data class ProfileUiState(
    val user: UserFirebase? = null,
    var newUri: Uri? = null,
    var newPseudo: String? = null,
    var errorMessage: String? = null
)