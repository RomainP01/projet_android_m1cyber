package com.sauce_hannibal.projet_android_m1cyber.repository.database

interface DatabaseRepository {
    fun getLeaderboard(callback: (Map<String, Long>) -> Unit)
}