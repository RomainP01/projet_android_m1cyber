package com.sauce_hannibal.projet_android_m1cyber.domain

import java.util.*

data class UserFirebase(
    val uid: String? = null,
    val pseudo: String? = null,
    val profilePictureUrl: String? = null,
    val lastTimeDailyAnswered: Date? = null,
    val dailyScore: Int? = null,
    val allTimeScore: Int? = null,
)