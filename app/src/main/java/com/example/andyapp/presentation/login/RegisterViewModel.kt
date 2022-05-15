package com.example.andyapp.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andyapp.data.repository.LoginRepository
import com.example.andyapp.data.Register
import com.example.andyapp.data.models.User
import com.example.andyapp.presentation.BaseViewModel
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: LoginRepository) : BaseViewModel() {

    private val _state = MutableLiveData<RegisterState>()
    private val login = MutableLiveData<User>()
    private val onLoginError = MutableLiveData<Unit>()

    fun doRegister(
        name: String,
        username: String,
        address: String,
        country: String,
        phone: String,
        email: String,
        password: String,
        confirmPass: String
    ) {
        viewModelScope.launch {
            _state.value = RegisterState.Loading
            if (!validateFields(name, username, address, country, phone, email, password, confirmPass)) return@launch
            when (repository.verifyUniqueUser(username, password)) {
                Register.AVAILABLE -> {
                    handleRegistration(
                        repository.register(
                            name,
                            username,
                            address,
                            country,
                            phone,
                            email,
                            password
                        )
                    )
                }
                Register.ALREADY_EXIST -> {
                    //show user already exist message
                    _state.value = RegisterState.AlreadyExist
                }
                else -> {
                    //show a generic error
                    _state.value = RegisterState.Error
                }
            }
        }
    }

    private fun validateFields(
        name: String,
        username: String,
        address: String,
        country: String,
        phone: String,
        email: String,
        password: String,
        confirmPass: String
    ) : Boolean {

        when {
            name.isEmpty() || username.isEmpty() ||
                    username.isEmpty() || address.isEmpty() || country.isEmpty() || phone.isEmpty() ||
                    email.isEmpty() || password.isEmpty() || email.isEmpty() || confirmPass.isEmpty() -> {
                _state.value = RegisterState.EmptyFields
                return false
            }

            password != confirmPass -> {
                _state.value = RegisterState.PasswordNotMatch
                return false
            }

            else -> return true

        }

    }

    private fun handleRegistration(register: Register) {
        when (register) {
            Register.REGISTER_SUCCESS -> {
                _state.value = RegisterState.Success
            }
            Register.ALREADY_EXIST -> {
                _state.value = RegisterState.AlreadyExist
            }
            Register.REGISTER_ERROR -> {
                _state.value = RegisterState.Error
            }
            Register.AVAILABLE -> {

            }
        }



    }

    fun login(username: String, pass: String) {
        viewModelScope.launch {
            val result = repository.login(
                username, pass
            )
            if (result!=null) {
                login.value = result!!
                user = result
            } else {
                onLoginError.value = Unit
            }

        }
    }

    fun state(): LiveData<RegisterState> = _state
    fun onLoginSuccess(): LiveData<User> = login
    fun onLoginError(): LiveData<Unit> = onLoginError

}



sealed class RegisterState() {
    object Loading : RegisterState()
    object Success : RegisterState()
    object AlreadyExist : RegisterState()
    object EmptyFields : RegisterState()
    object PasswordNotMatch : RegisterState()
    object Error : RegisterState()
}