package com.sauce_hannibal.projet_android_m1cyber.ui.screens.game

import com.sauce_hannibal.projet_android_m1cyber.domain.TrivialPursuitQuestion

data class GameUiState(
    var questions: List<TrivialPursuitQuestion> = listOf(),
    val currentQuestion: TrivialPursuitQuestion? = null,
    val possibleAnswers: List<String> = listOf(),
    val userScore: Int = 0,
    var numberOfQuestionsAnswered: Int = 0,
    val numberOfQuestions: Int = 0,
    val answerSelected: String? = null,
    val isAnswerCorrect: Boolean? = null,
    var isEnded: Boolean = false,
    val timer: Int = 10,
    val multiplier: Double = 1.0,
)