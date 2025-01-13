package com.example.myapplication.models

data class ExerciseList(
    val exercises: List<Exercise>,
    val subject: Subject,
    val grade: Double
)