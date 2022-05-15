package com.example.andyapp.presentation.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.andyapp.data.models.Quiz
import com.example.andyapp.data.models.Topic
import com.example.andyapp.presentation.BaseViewModel
import com.example.andyapp.presentation.Event

class QuizViewModel : BaseViewModel() {

    private lateinit var quiz: Quiz

    private lateinit var topic: Topic

    private val nextQuestion = MutableLiveData<Event<Unit>>()

    private val scoreScreen = MutableLiveData<Event<Unit>>()

    private val userAnswer = mutableMapOf<String, String>()

    private var userScore = 0

    private var position = 0

    fun setQuiz(quiz: Quiz) {
        this.quiz = quiz
    }

    fun setTopic(topic: Topic) {
        this.topic = topic
    }

    fun nexQuestion() {
        if (position == topic.quiz.size - 1) {
            scoreScreen.value = Event(Unit)
        } else {
            nextQuestion.value = Event(Unit)
        }
    }

    fun setUserAnswers(question: String, answer: String) {
        userAnswer[question] = answer
    }

    fun validateAnswers() {
        topic.quiz.forEach {
            if (userAnswer[it.question] == it.answer) {
                userScore++
            }
        }
    }

    fun getScore(): Int = userScore

    fun setCurrentPage(position: Int) {
        this.position = position
    }

    fun reset() {
        position = 0
        userScore = 0
    }

    fun onNextQuestion(): LiveData<Event<Unit>> = nextQuestion
    fun onScoreScreen(): LiveData<Event<Unit>> = scoreScreen
}