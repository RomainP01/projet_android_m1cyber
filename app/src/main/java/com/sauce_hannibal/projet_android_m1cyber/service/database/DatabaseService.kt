package com.sauce_hannibal.projet_android_m1cyber.service.database

interface DatabaseService {
    fun getLeaderboard(callback: (Map<String, Long>) -> Unit)
}