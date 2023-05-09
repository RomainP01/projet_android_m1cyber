package com.sauce_hannibal.projet_android_m1cyber.domain

import java.util.Date

data class UserFirebase(
    val uid: String? = null,
    val pseudo: String? = null,
    val lastTimeDailyAnswered: Date? = null,
)