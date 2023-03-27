package com.sauce_hannibal.projet_android_m1cyber.repository

import com.sauce_hannibal.projet_android_m1cyber.domain.Details
import com.sauce_hannibal.projet_android_m1cyber.network.DetailsApi
import javax.inject.Inject

class DetailsRepository @Inject constructor(private val detailsApi: DetailsApi) {

    suspend fun getUserDetails(user: String): Details {
        val detail = detailsApi.getDetails(user)
        return Details(
            user = "",
            avatar = detail.avatarUrl,
            name = detail.name,
            userSince = detail.createdAt,
            location = detail.location
        )
    }

}