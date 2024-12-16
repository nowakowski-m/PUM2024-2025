package com.example.lista4

data class Question(
    val text: String,
    val answers: List<String>,
    val correctAnswerIndex: Int
)

