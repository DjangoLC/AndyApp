package com.example.andyapp.data.models

import java.io.Serializable

data class Topic(
    val title: String,
    val quiz: List<Quiz> = emptyList()
) : Serializable {

    constructor() : this(
        title = "",
        quiz = emptyList()
    )
}

data class Quiz(
    val question: String,
    val answer: String,
    val possibleAnswers: List<String>
) : Serializable {
    constructor() : this(
        question = "",
        answer = "",
        possibleAnswers = emptyList()
    )
}