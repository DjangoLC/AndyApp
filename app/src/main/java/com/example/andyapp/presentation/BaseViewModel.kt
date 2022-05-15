package com.example.andyapp.presentation

import androidx.lifecycle.ViewModel
import com.example.andyapp.data.models.User

open class BaseViewModel :ViewModel() {

    var user: User? = null

}