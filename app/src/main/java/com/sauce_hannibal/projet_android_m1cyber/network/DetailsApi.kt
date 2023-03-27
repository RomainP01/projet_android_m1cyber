package com.sauce_hannibal.projet_android_m1cyber.network

import com.sauce_hannibal.projet_android_m1cyber.network.model.DetailsApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsApi {

    @GET("/users/{user}")
    suspend fun getDetails(@Path("user") user: String): DetailsApiModel
}