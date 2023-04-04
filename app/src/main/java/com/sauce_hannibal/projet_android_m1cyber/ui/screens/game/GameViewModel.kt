package com.sauce_hannibal.projet_android_m1cyber.ui.screens.game

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sauce_hannibal.projet_android_m1cyber.domain.TrivialPursuitQuestion
import com.sauce_hannibal.projet_android_m1cyber.repository.TrivialPursuitQuestionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class GameViewModel @Inject constructor(
    private val trivialPursuitQuestionsRepository: TrivialPursuitQuestionsRepository
) : ViewModel() {
    private val _gameUiState = MutableStateFlow(GameUiState())
    val gameUiState: StateFlow<GameUiState>
        get() = _gameUiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val questionList = trivialPursuitQuestionsRepository.getQuestions()
            withContext(Dispatchers.Main) {
                if (questionList.isEmpty()) {
                    _gameUiState.value = _gameUiState.value.copy(
                        questions = listOf(),
                        currentQuestion = null,
                        possibleAnswers = listOf(),
                        numberOfQuestions = 0,
                        numberOfQuestionsAnswered = 0,
                        userScore = 0,
                        isAnswerCorrect = null,
                        answerSelected = null,
                        timer = 0
                    )
                } else {
                    val currentQuestion = questionList[0]
                    val possibleAnswers = createPossibleAnswers(currentQuestion)
                    _gameUiState.value = _gameUiState.value.copy(
                        questions = questionList,
                        currentQuestion = currentQuestion,
                        possibleAnswers = possibleAnswers,
                        numberOfQuestions = 20,
                        numberOfQuestionsAnswered = 0,
                        userScore = 0,
                        isAnswerCorrect = null,
                        answerSelected = null,
                        timer = 20
                    )
                }
            }
        }
    }

    private fun resetGameUiState() {
        _gameUiState.value = _gameUiState.value.copy(
            questions = listOf(),
            currentQuestion = null,
            possibleAnswers = listOf(),
            numberOfQuestions = 0,
            numberOfQuestionsAnswered = 0,
            userScore = 0,
            isAnswerCorrect = null,
            answerSelected = null,
            timer = 0
        )
    }


    private fun createPossibleAnswers(currentQuestion: TrivialPursuitQuestion): List<String> {
        val possibleAnswers = mutableListOf<String>()
        possibleAnswers.add(currentQuestion.correctAnswer)
        possibleAnswers.addAll(currentQuestion.incorrectAnswers)
        return possibleAnswers.shuffled()
    }


    private fun changeToNextQuestion() {
        if (gameUiState.value.numberOfQuestionsAnswered == gameUiState.value.numberOfQuestions) {
            _gameUiState.value = _gameUiState.value.copy(
                currentQuestion = null,
                possibleAnswers = listOf(),
                isAnswerCorrect = null,
                answerSelected = null,
                timer = 20
            )
            return
        }
        val currentQuestion =
            gameUiState.value.questions[gameUiState.value.numberOfQuestionsAnswered]
        val possibleAnswers = createPossibleAnswers(currentQuestion)
        _gameUiState.value = _gameUiState.value.copy(
            currentQuestion = currentQuestion,
            possibleAnswers = possibleAnswers,
            numberOfQuestionsAnswered = gameUiState.value.numberOfQuestionsAnswered + 1,
            isAnswerCorrect = null,
            answerSelected = null
        )
    }

    fun onAnswerClick(answer: String) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _gameUiState.value = _gameUiState.value.copy(
                    answerSelected = answer,
                    isAnswerCorrect = answer == gameUiState.value.currentQuestion?.correctAnswer,
                    userScore = if (answer == gameUiState.value.currentQuestion?.correctAnswer) gameUiState.value.userScore + 1 else gameUiState.value.userScore,
                    timer = 20
                )
                delay(1000)
                changeToNextQuestion()
            }
        }

    }

    fun changeColorOfButton(answer: String): Color {
        if (answer == gameUiState.value.answerSelected) {
            return if (gameUiState.value.currentQuestion?.correctAnswer == answer) {
                Color.Green
            } else {
                Color.Red
            }
        }
        if (gameUiState.value.currentQuestion?.correctAnswer == answer && gameUiState.value.answerSelected != null) {
            return Color.Green
        }
        return Color.Transparent
    }

}