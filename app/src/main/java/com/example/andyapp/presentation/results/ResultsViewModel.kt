package com.example.andyapp.presentation.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andyapp.data.models.Score
import com.example.andyapp.data.repository.LoginRepository
import com.example.andyapp.data.repository.QuestionsRepository
import com.example.andyapp.presentation.BaseViewModel
import kotlinx.coroutines.launch

class ResultsViewModel(private val repository: QuestionsRepository, private val loginRepository: LoginRepository) : BaseViewModel() {

    private val results = MutableLiveData<List<Score>>()

    fun getResults() {
        viewModelScope.launch {
            results.value = repository.getResults()
        }
    }

    fun saveResult(score: Int) {
        viewModelScope.launch {
            repository.saveScore(Score(score,loginRepository.getCurrentUser()?.name ?:"unknown user"))
        }
    }

    fun onGetResults(): LiveData<List<Score>> = results
}