package com.sauce_hannibal.projet_android_m1cyber.repository.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import com.sauce_hannibal.projet_android_m1cyber.domain.LeaderBoardFirebaseItem
import com.sauce_hannibal.projet_android_m1cyber.domain.UserFirebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LeaderboardFirebaseRepository @Inject constructor(private val firestore: FirebaseFirestore) {
    suspend fun insertOrUpdateScore(userId: String, score: Int) {
        val leaderboardCollection = firestore.collection(_collection).document(userId)

        firestore.runTransaction { transaction ->
            val snapshot = transaction.get(leaderboardCollection)

            if (snapshot.exists()) {
                val currentAllTimeScore = snapshot.getLong("allTimeScore") ?: 0
                transaction.update(leaderboardCollection, "dailyScore", score)
                if (score > currentAllTimeScore) {
                    transaction.update(
                        leaderboardCollection,
                        "allTimeScore",
                        score
                    )
                } else {

                }
            } else {
                transaction.set(
                    leaderboardCollection,
                    LeaderBoardFirebaseItem(score, score)
                )
            }
        }.await()

    }

    fun getAll(): Flow<List<LeaderBoardFirebaseItem>> {
        return firestore.collection(_collection).snapshots()
            .map() { it.toObjects<LeaderBoardFirebaseItem>() }
    }

    companion object {
        private const val _collection = "SCORES"
    }
}