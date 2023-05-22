package com.sauce_hannibal.projet_android_m1cyber.repository.storage

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class FirebaseStorageRepository @Inject constructor(
    private val firebaseStorage: FirebaseStorage,
) {
    fun uploadImage(
        imageUri: Uri,
        userId: String,
        successCallback: (String) -> Unit,
        errorCallback: (Exception) -> Unit
    ) {
        val imagesRef = firebaseStorage.reference.child("images/$userId/profile_picture.jpg")
        val uploadTask = imagesRef.putFile(imageUri)

        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            imagesRef.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
                successCallback.invoke(downloadUri.toString())
            } else {
                task.exception?.let {
                    errorCallback.invoke(it)
                }
            }
        }
    }

}