package com.sauce_hannibal.projet_android_m1cyber.repository

import com.sauce_hannibal.projet_android_m1cyber.domain.User
import com.sauce_hannibal.projet_android_m1cyber.network.UsersApi
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