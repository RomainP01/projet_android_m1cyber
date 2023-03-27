package com.mvince.compose.network

import com.mvince.compose.network.model.DetailsApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsApi {

    @GET("/users/{user}")
    suspend fun getDetails(@Path("user") user: String): DetailsApiModel
}