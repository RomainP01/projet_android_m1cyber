package com.sauce_hannibal.projet_android_m1cyber.domain

data class TrivialPursuitQuestion(
    val category: String,
    val id: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>,
    val question: String,
    val tags: List<String>,
    val type: String,
    val difficulty: String,
    val regions: List<String>,
    val isNiche: Boolean
)