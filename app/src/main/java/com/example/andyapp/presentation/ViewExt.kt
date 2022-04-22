package com.example.andyapp.presentation

import android.view.View

fun View.isVisible(visibility: Boolean) {
    this.visibility = if (visibility) View.VISIBLE else View.GONE
}