package com.sauce_hannibal.projet_android_m1cyber.di

import com.google.firebase.auth.FirebaseAuth
import com.sauce_hannibal.projet_android_m1cyber.BuildConfig
import com.sauce_hannibal.projet_android_m1cyber.network.TrivialPursuitQuestionsApi
import com.sauce_hannibal.projet_android_m1cyber.service.account.AccountService
import com.sauce_hannibal.projet_android_m1cyber.service.account.FirebaseAccountService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://the-trivia-api.com/api/")
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): TrivialPursuitQuestionsApi = retrofit.create(TrivialPursuitQuestionsApi::class.java)


    @Provides
    @Singleton
    fun provideFirebaseAccountService(): FirebaseAccountService = FirebaseAccountService(
        FirebaseAuth.getInstance()
    );

    @Provides
    @Singleton
    fun provideAccountService(): AccountService = FirebaseAccountService(
        FirebaseAuth.getInstance()
    )

}