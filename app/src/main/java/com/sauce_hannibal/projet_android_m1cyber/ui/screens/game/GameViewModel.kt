package com.sauce_hannibal.projet_android_m1cyber.ui.screens.game

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sauce_hannibal.projet_android_m1cyber.domain.TrivialPursuitQuestion
import com.sauce_hannibal.projet_android_m1cyber.domain.UserFirebase
import com.sauce_hannibal.projet_android_m1cyber.repository.account.AccountRepository
import com.sauce_hannibal.projet_android_m1cyber.repository.api.TrivialPursuitQuestionsRepository
import com.sauce_hannibal.projet_android_m1cyber.repository.firestore.UserFirebaseRepository
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.Green100
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.Red100
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import java.util.*
import javax.inject.Inject


@HiltViewModel
class GameViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val userFirebaseRepository: UserFirebaseRepository,
    private val trivialPursuitQuestionsRepository: TrivialPursuitQuestionsRepository,
) : ViewModel() {
    private val _gameUiState = MutableStateFlow(GameUiState())
    val gameUiState: StateFlow<GameUiState>
        get() = _gameUiState


    init {
        viewModelScope.launch(Dispatchers.IO) {
            val user = accountRepository.getUserLoggedIn()
            if (user != null) {
                userFirebaseRepository.getUser(user.uid).collectLatest { userFirebase ->
                    _gameUiState.value = _gameUiState.value.copy(user = userFirebase)
                    loadQuestions()
                    _gameUiState.value = _gameUiState.value.copy(isStarted = true)
                }
            }

        }
    }

    private suspend fun loadQuestions() {
        val questions = trivialPursuitQuestionsRepository.getQuestions()
        val currentQuestion = questions[0]
        val possibleAnswers = createPossibleAnswers(currentQuestion)

        _gameUiState.value = _gameUiState.value.copy(
            questions = questions,
            currentQuestion = currentQuestion,
            possibleAnswers = possibleAnswers,
            numberOfQuestions = questions.size,
            numberOfQuestionsAnswered = 0,
            userScore = 0,
            isAnswerCorrect = null,
            answerSelected = null,
            timer = 10,
            multiplier = 1.0,
        )
    }


    private fun createPossibleAnswers(currentQuestion: TrivialPursuitQuestion): List<String> {
        val possibleAnswers = mutableListOf<String>()
        possibleAnswers.add(currentQuestion.correctAnswer)
        possibleAnswers.addAll(currentQuestion.incorrectAnswers)
        return possibleAnswers.shuffled()
    }

    fun handleEndOfGame() {
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }.time
        gameUiState.value.user?.uid?.let {
            userFirebaseRepository.updateScores(it, gameUiState.value.userScore)
            userFirebaseRepository.updateLastTimeDailyAnswered(it, today)
            _gameUiState.value = _gameUiState.value.copy(isEnded = true)
        }
    }


    private fun changeToNextQuestion() {
        val numberOfQuestionsAnswered = gameUiState.value.numberOfQuestionsAnswered + 1
        if (numberOfQuestionsAnswered == gameUiState.value.numberOfQuestions) {
            handleEndOfGame()
            return
        }
        val currentQuestion =
            gameUiState.value.questions[numberOfQuestionsAnswered]
        val possibleAnswers = createPossibleAnswers(currentQuestion)
        _gameUiState.value = _gameUiState.value.copy(
            currentQuestion = currentQuestion,
            possibleAnswers = possibleAnswers,
            numberOfQuestionsAnswered = numberOfQuestionsAnswered,
            isAnswerCorrect = null,
            answerSelected = null,
            timer = 10
        )
    }

    fun handleTimerEnd() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _gameUiState.value = _gameUiState.value.copy(
                    isAnswerCorrect = false,
                    answerSelected = ""
                )
                delay(1000)
                changeToNextQuestion()
            }
        }
    }

    private fun calculateNewScore(score: Int, difficulty: String, multiplier: Double): Int {
        val pointToAdd = if (difficulty == "easy") 10 else if (difficulty == "medium") 25 else 50
        return (pointToAdd * multiplier + score).toInt()
    }

    private fun calculateMultiplier(multiplier: Double): Double {
        if (multiplier < 5.0) {
            return multiplier + 0.5
        }
        return multiplier
    }

    fun onAnswerClick(answer: String) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                val score =
                    if (answer == gameUiState.value.currentQuestion?.correctAnswer) calculateNewScore(
                        gameUiState.value.userScore,
                        gameUiState.value.currentQuestion?.difficulty!!,
                        gameUiState.value.multiplier
                    ) else gameUiState.value.userScore
                _gameUiState.value = _gameUiState.value.copy(
                    answerSelected = answer,
                    isAnswerCorrect = answer == gameUiState.value.currentQuestion?.correctAnswer,
                    userScore = score,
                    timer = 10,
                    multiplier = if (answer == gameUiState.value.currentQuestion?.correctAnswer) calculateMultiplier(
                        gameUiState.value.multiplier
                    ) else 1.0
                )
                delay(1000)
                changeToNextQuestion()
            }
        }

    }

    fun changeColorOfButton(answer: String): Color {
        if (answer == gameUiState.value.answerSelected) {
            return if (gameUiState.value.currentQuestion?.correctAnswer == answer) {
                Green100
            } else {
                Red100
            }
        }
        if (gameUiState.value.currentQuestion?.correctAnswer == answer && gameUiState.value.answerSelected != null) {
            return Green100
        }
        return Color.Transparent
    }

    fun changeColorOfDifficulty(difficulty: String): Color {
        return when (difficulty) {
            "easy" -> Green100
            "medium" -> Color.Yellow
            "hard" -> Red100
            else -> Color.Transparent
        }
    }

    fun setIsOpenPopUp(isOpenPopUp: Boolean) {
        _gameUiState.value = _gameUiState.value.copy(isOpenPopUp = isOpenPopUp)
    }

    fun changeColorOfMultiplier(multiplier: Double): Color {
        return when (multiplier) {
            in 1.0..2.0 -> Color.Yellow
            in 2.0..3.5 -> Color(red = 255, green = 136, blue = 0)
            in 3.5..5.0 -> Red100
            else -> {
                Color.Yellow
            }
        }
    }

    fun changeColorOfTimer(timer: Float): Color {
        return when {
            timer > 0.55 -> Green100
            timer > 0.20 -> Color(red = 255, green = 136, blue = 0)
            else -> Red100
        }
    }

}