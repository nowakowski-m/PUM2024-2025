package com.example.lista7.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lista7.model.Student
import com.example.lista7.model.studentsList

class StudentsViewModel : ViewModel() {
    private var _students = studentsList

    val students: List<Student>
        get() = _students
}
