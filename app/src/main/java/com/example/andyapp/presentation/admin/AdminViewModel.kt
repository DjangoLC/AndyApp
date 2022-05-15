package com.example.andyapp.presentation.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andyapp.data.repository.QuestionsRepository
import com.example.andyapp.data.models.Quiz
import com.example.andyapp.data.models.Topic
import com.example.andyapp.presentation.BaseViewModel
import kotlinx.coroutines.launch

class AdminViewModel(private val repository: QuestionsRepository) : BaseViewModel() {

    private val topics = MutableLiveData<List<Topic>>()
    private val questionSaved = MutableLiveData<Boolean>()

    private var id = ""

    fun getTopics() {
        viewModelScope.launch {
            val data = repository.getTopics()
            topics.value = data
        }
    }

    fun setId(id: String) {
        this.id = id
    }

    fun saveQuestion(
        question: String,
        correctAnswer: String,
        possibleAnswers: List<String>
    ) {

        if (question.isEmpty() || correctAnswer.isEmpty() || possibleAnswers.any { it.isEmpty() } || id.isEmpty()) {
            return
        }

        val mQuiz = Quiz(
            question = question,
            answer = correctAnswer,
            possibleAnswers = possibleAnswers
        )
        viewModelScope.launch {
            val currentTopics = topics.value?.firstOrNull { it.id == id }
            val quiz = currentTopics?.quiz ?: emptyList()
            val mutable = quiz.toMutableList()
            mutable.add(mQuiz)
            val isSaved = repository.createQuestionByTopic(id, mutable)
            questionSaved.value = isSaved
        }

    }

    fun onGetTopics(): LiveData<List<Topic>> = topics

    fun onQuestionSaved(): LiveData<Boolean> = questionSaved

}