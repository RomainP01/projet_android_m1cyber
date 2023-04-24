package com.sauce_hannibal.projet_android_m1cyber.repository.firestore

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import com.sauce_hannibal.projet_android_m1cyber.domain.LeaderBoardFirebaseItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LeaderboardFirebaseRepository @Inject constructor(private val firestore: FirebaseFirestore) {
    fun insertUser(id: String, user: FirebaseUser): Boolean {
        return firestore.collection(_collection).document(id).set(user).isSuccessful
    }

    fun getAll(): Flow<List<LeaderBoardFirebaseItem>> {
        return firestore.collection(_collection).snapshots().map() { it.toObjects<LeaderBoardFirebaseItem>() }
    }

    companion object {
        private const val _collection = "BEST_SCORES"
    }
}