package com.sauce_hannibal.projet_android_m1cyber.repository.database

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirestoreDatabaseRepository @Inject constructor(private val firestore: FirebaseFirestore) :
    DatabaseRepository {


    override fun getLeaderboard(callback: (Map<String, Long>) -> Unit) {
        val leaderboard = mutableMapOf<String, Long>()
        try {
            firestore.collection("userScore").get().addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    val username = document.getString("username")
                    val score = document.getLong("score")
                    if (username != null && score != null) {
                        leaderboard[username] = score
                    }
                }
                callback(leaderboard.toList().sortedByDescending { (_, value) -> value }.toMap())
            }.addOnFailureListener { e ->
                e.printStackTrace()
                callback(emptyMap())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            callback(emptyMap())
        }
    }
}