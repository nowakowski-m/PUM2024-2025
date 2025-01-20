package com.example.lista7.model

data class Student(
    val indexNumber: String,
    val firstName: String,
    val lastName: String,
    val averageGrade: Double,
    val studyYear: Int
)

val studentsList = listOf(
    Student("1001", "Anna", "Kowalska", 4.5, 1),
    Student("1002", "Jan", "Nowak", 3.8, 2),
    Student("1003", "Maria", "Wiśniewska", 4.2, 3),
    Student("1004", "Paweł", "Wójcik", 3.5, 2),
    Student("1005", "Karolina", "Lewandowska", 4.9, 1),
    Student("1006", "Kacper", "Krawczyk", 3.6, 2),
    Student("1007", "Damian", "Polak", 4.5, 3),
    Student("1008", "Michał", "Dobrowolski", 4.3, 2),
)