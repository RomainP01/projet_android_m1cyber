package com.sauce_hannibal.projet_android_m1cyber.repository.api


import com.sauce_hannibal.projet_android_m1cyber.domain.TrivialPursuitQuestion
import com.sauce_hannibal.projet_android_m1cyber.network.TrivialPursuitQuestionsApi
import javax.inject.Inject

class TrivialPursuitQuestionsRepository @Inject constructor(private val trivialPursuitQuestionsApi: TrivialPursuitQuestionsApi) {

    suspend fun getQuestions(): List<TrivialPursuitQuestion> {
        val trivialPursuitQuestions = trivialPursuitQuestionsApi.getQuestions()
        return trivialPursuitQuestions.map {
            TrivialPursuitQuestion(
                category = it.category,
                id = it.id,
                correctAnswer = it.correctAnswer,
                incorrectAnswers = it.incorrectAnswers,
                question = it.question,
                tags = it.tags,
                type = it.type,
                difficulty = it.difficulty,
                regions = it.regions,
                isNiche = it.isNiche
            )
        }
    }
}