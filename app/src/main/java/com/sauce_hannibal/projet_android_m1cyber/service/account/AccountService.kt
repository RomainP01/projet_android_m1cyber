package com.sauce_hannibal.projet_android_m1cyber.service.account

import com.sauce_hannibal.projet_android_m1cyber.domain.UserFirebase

interface AccountService {
    val currentUser : UserFirebase
    fun login(email: String, password: String)
    fun logout()
    fun linkAccount(email: String, password: String)
}