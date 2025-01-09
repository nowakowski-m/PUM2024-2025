package com.example.lista4

data class Question(
    val index: Int,
    val text: String,
    val answers: List<String>,
    val correctAnswerIndex: Int
)