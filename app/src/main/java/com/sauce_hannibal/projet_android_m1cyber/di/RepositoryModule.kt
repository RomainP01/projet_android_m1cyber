package com.sauce_hannibal.projet_android_m1cyber.di

import com.sauce_hannibal.projet_android_m1cyber.repository.account.AccountRepository
import com.sauce_hannibal.projet_android_m1cyber.repository.account.FirebaseAccountRepository
import com.sauce_hannibal.projet_android_m1cyber.repository.database.DatabaseRepository
import com.sauce_hannibal.projet_android_m1cyber.repository.database.FirestoreDatabaseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

        @Binds
        abstract fun bindAccountRepository(firebaseAccountRepository: FirebaseAccountRepository): AccountRepository

        @Binds
        abstract fun bindDatabaseRepository(firestoreDatabaseRepository: FirestoreDatabaseRepository): DatabaseRepository

}