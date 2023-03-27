package com.mvince.compose.repository

import com.mvince.compose.domain.Details
import com.mvince.compose.network.DetailsApi
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