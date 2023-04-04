package com.sauce_hannibal.projet_android_m1cyber.network

import com.sauce_hannibal.projet_android_m1cyber.network.model.TrivialPursuitQuestionsModel
import retrofit2.http.GET

interface TrivialPursuitQuestionsApi {

    @GET("questions")
    suspend fun getQuestions(): List<TrivialPursuitQuestionsModel>
}