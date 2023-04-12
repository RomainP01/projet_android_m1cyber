package com.sauce_hannibal.projet_android_m1cyber.repository.account

import com.sauce_hannibal.projet_android_m1cyber.domain.UserFirebase

interface AccountRepository {
    val currentUser : UserFirebase
    fun login(email: String, password: String)
    fun logout()
    fun linkAccount(email: String, password: String)
}