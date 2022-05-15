package com.example.andyapp.presentation.login

import android.text.Editable
import android.text.TextWatcher

class PasswordWatcher(private val validations: List<Validations>, private val callback: PasswordListener) :
    TextWatcher {
    interface PasswordListener {
        fun onValidate(type: Validations?, isValid: Boolean?)
    }

    private val errorsMap = mutableMapOf<Validations, Boolean>()

    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
        validations.forEach {
            errorsMap[it] = it.regex.toRegex().matches(charSequence)
            callback.onValidate(it, errorsMap[it] ?: false)
        }
    }
    override fun afterTextChanged(editable: Editable) {}
}

enum class Validations(val regex: String) {
    LENGTH(""),
    CHARACTER("[_|*]|[^\\w\\*]"),
    UPPERCASE("^.*([A-Z]+).*$");
}