package com.sauce_hannibal.projet_android_m1cyber.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sauce_hannibal.projet_android_m1cyber.repository.account.FirebaseAccountRepository
import com.sauce_hannibal.projet_android_m1cyber.repository.database.FirestoreDatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    @Provides
    @Singleton
    fun provideFirebaseAccountRepository() : FirebaseAccountRepository = FirebaseAccountRepository(
        FirebaseAuth.getInstance()
    )

    @Provides
    @Singleton
    fun provideFirestoreDatabaseRepository(): FirestoreDatabaseRepository = FirestoreDatabaseRepository(
        FirebaseFirestore.getInstance()
    )
}