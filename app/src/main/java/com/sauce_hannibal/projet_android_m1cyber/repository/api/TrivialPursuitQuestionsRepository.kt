package com.sauce_hannibal.projet_android_m1cyber.repository.api


import com.google.firebase.firestore.FirebaseFirestore
import com.sauce_hannibal.projet_android_m1cyber.domain.TrivialPursuitQuestion
import com.sauce_hannibal.projet_android_m1cyber.network.TrivialPursuitQuestionsApi
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject

class TrivialPursuitQuestionsRepository @Inject constructor(
    private val trivialPursuitQuestionsApi: TrivialPursuitQuestionsApi,
    private val firestore: FirebaseFirestore
) {

    private fun insertQuestions(questions: List<TrivialPursuitQuestion>): Boolean {
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }.time

        val questionsCollection = firestore.collection(_collection)
            .document(today.toString())
            .collection("questions")

        questions.forEach { question ->
            questionsCollection.document(question.id).set(question)
        }

        return true
    }


    suspend fun getQuestions(): List<TrivialPursuitQuestion> {
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }.time

        val questionsCollection = firestore.collection(_collection)
            .document(today.toString())
            .collection("questions")

        val querySnapshot = questionsCollection.get().await()
        return if (querySnapshot.size() > 0) {
            print(querySnapshot)
            querySnapshot.map {
                TrivialPursuitQuestion(
                    category = it["category"] as String,
                    id = it["id"] as String,
                    correctAnswer = it["correctAnswer"] as String,
                    incorrectAnswers = it["incorrectAnswers"] as List<String>,
                    question = it["question"] as String,
                    tags = it["tags"] as List<String>,
                    type = it["type"] as String,
                    difficulty = it["difficulty"] as String,
                    regions = it["regions"] as List<String>,
                    isNiche = it["isNiche"] as? Boolean ?: false
                )
            }
        } else {
            val trivialPursuitQuestionsModel = trivialPursuitQuestionsApi.getQuestions()
            val trivialPursuitQuestions = trivialPursuitQuestionsModel.map {
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
            insertQuestions(trivialPursuitQuestions)
            trivialPursuitQuestions
        }
    }


    companion object {
        private const val _collection = "QUESTIONS"
    }
}