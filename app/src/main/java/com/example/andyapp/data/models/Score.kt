package com.example.andyapp.data.models

data class Score(
    val score: Int,
    val userName: String
) {
    constructor(): this(0,"")
}