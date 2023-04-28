package com.sauce_hannibal.projet_android_m1cyber.repository.account

import com.google.firebase.auth.FirebaseUser
import com.sauce_hannibal.projet_android_m1cyber.domain.UserFirebase

interface AccountRepository {
    val currentUser : UserFirebase
    fun login(email: String, password: String)
    fun logout()
    fun linkAccount(email: String, password: String)
    suspend fun signUp(email: String, password: String): FirebaseUser?
    suspend fun getUserLoggedIn(): FirebaseUser?
}