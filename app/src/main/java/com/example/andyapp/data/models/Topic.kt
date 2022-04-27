package com.example.andyapp.data.models

import java.io.Serializable
import java.util.*

data class Topic(
    val title: String,
    val quiz: List<Quiz> = emptyList(),
    var id: String = UUID.randomUUID().toString()
) : Serializable {

    constructor() : this(
        title = "",
        quiz = emptyList()
    )

    override fun toString(): String {
        return title
    }
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