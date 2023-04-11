package com.sauce_hannibal.projet_android_m1cyber.service.database

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreDatabaseService @Inject constructor(private val firestore: FirebaseFirestore) :
    DatabaseService {


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