package com.example.andyapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.andyapp.data.LoginRepository
import com.example.andyapp.data.QuestionsRepository
import com.example.andyapp.presentation.login.RegisterViewModel
import com.example.andyapp.presentation.main.TopicsViewModel
import com.example.andyapp.presentation.quiz.QuizViewModel

class ViewModelFactory : ViewModelProvider.Factory {

    private val repository = LoginRepository()
    private val topicRepository = QuestionsRepository()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(repository) as T
        }

        if (modelClass.isAssignableFrom(TopicsViewModel::class.java)) {
            return TopicsViewModel(topicRepository) as T
        }

        if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            return QuizViewModel() as T
        }

        throw Exception("cant create view model: ${modelClass.canonicalName}")


    }
}
