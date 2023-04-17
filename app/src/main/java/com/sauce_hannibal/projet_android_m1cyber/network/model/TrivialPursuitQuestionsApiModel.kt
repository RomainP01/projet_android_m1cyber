package com.sauce_hannibal.projet_android_m1cyber.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrivialPursuitQuestionsModel(
    @Json(name = "category")
    val category: String = "",
    @Json(name = "id")
    val id: String = "",
    @Json(name = "correctAnswer")
    val correctAnswer: String = "",
    @Json(name = "incorrectAnswers")
    val incorrectAnswers: List<String> = listOf(),
    @Json(name = "question")
    val question: String = "",
    @Json(name = "tags")
    val tags: List<String> = listOf(),
    @Json(name = "type")
    val type: String = "",

    @Json(name = "difficulty")
    val difficulty: String = "",
    @Json(name = "regions")
    val regions: List<String> = listOf(),
    @Json(name = "isNiche")
    val isNiche: Boolean = false
)