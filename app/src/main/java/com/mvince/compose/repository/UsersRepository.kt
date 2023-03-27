package com.mvince.compose.repository

import com.mvince.compose.domain.User
import com.mvince.compose.network.UsersApi
import javax.inject.Inject

class UsersRepository @Inject constructor(private val usersApi: UsersApi) {

    suspend fun getUsers(): List<User> {
        val users = usersApi.getUsers()
        return users.map { User(
            id = it.id,
            avatar = it.avatarUrl,
            username = it.login
        ) }
    }
}