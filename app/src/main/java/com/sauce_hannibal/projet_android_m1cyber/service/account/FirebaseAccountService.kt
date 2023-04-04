package com.sauce_hannibal.projet_android_m1cyber.service.account

import com.google.firebase.auth.FirebaseAuth
import com.sauce_hannibal.projet_android_m1cyber.domain.UserFirebase
import javax.inject.Inject

class FirebaseAccountService @Inject constructor(private val auth: FirebaseAuth) : AccountService {

    override val currentUser: UserFirebase
        get() = UserFirebase(auth.currentUser?.uid ?: "")

    override fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
            } else {
                println("signInWithEmail:failure")
                println(task.exception)
            }
        }
    }

    override fun logout() {
        auth.signOut()
    }

    override fun linkAccount(email: String, password: String) {
        println(email)
        println(password)
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
            } else {
                println("signInWithEmail:failure")
                println(task.exception)
            }
        }
    }

}