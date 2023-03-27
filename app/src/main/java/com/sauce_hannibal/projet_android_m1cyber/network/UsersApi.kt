package com.sauce_hannibal.projet_android_m1cyber.network

import com.sauce_hannibal.projet_android_m1cyber.network.model.UserApiModel
import retrofit2.http.GET

interface UsersApi {

    @GET("/repos/square/retrofit/stargazers")
    suspend fun getUsers(): List<UserApiModel>
}