package com.sauce_hannibal.projet_android_m1cyber.repository.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.sauce_hannibal.projet_android_m1cyber.domain.UserFirebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject

class UserFirebaseRepository @Inject constructor(private val firestore: FirebaseFirestore) {
    fun insertUser(id: String, user: UserFirebase): Boolean {
        return firestore.collection(_collection).document(id).set(user).isSuccessful
    }

    fun getAll(): Flow<List<UserFirebase>> {
        return firestore.collection(_collection).snapshots().map() { it.toObjects() }
    }

    fun getUser(id: String): Flow<UserFirebase> {
        return try {
            firestore.collection(_collection).document(id).snapshots().map() { it.toObject()!! }
        } catch (e: Exception) {
            throw e
        }
    }

    fun updateLastTimeDailyAnswered(id: String, lastTimeDailyAnswered: Date): Boolean {
        return firestore.collection(_collection).document(id)
            .update("lastTimeDailyAnswered", lastTimeDailyAnswered).isSuccessful
    }

    fun updateScores(id: String, score: Int) {
        firestore.collection(_collection).document(id).get().addOnSuccessListener {
            val allTimeScore = it.get("allTimeScore") as Long?
            if (allTimeScore == null) {
                firestore.collection(_collection).document(id)
                    .update("allTimeScore", score)
            } else {
                if (score > allTimeScore) {
                    firestore.collection(_collection).document(id)
                        .update("allTimeScore", score)
                }
            }
            firestore.collection(_collection).document(id)
                .update("dailyScore", score)

        }
    }

    suspend fun isPseudoAvailable(pseudo: String): Boolean {
        val queryToCheckIfUsernameTaken = firestore.collection(_collection)
            .whereEqualTo("pseudo", pseudo)
            .limit(1)

        val querySnapshot = queryToCheckIfUsernameTaken.get().await()
        if (querySnapshot.documents.isNotEmpty()) {
            return false
        }
        return true
    }

    fun updatePseudo(id: String, pseudo: String): Boolean {
        return firestore.collection(_collection)
            .document(id)
            .update("pseudo", pseudo)
            .isSuccessful
    }

    companion object {
        private const val _collection = "USERS"
    }
}