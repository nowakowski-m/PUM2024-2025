package com.example.lista8.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lista8.model.Grade
import com.example.lista8.model.GradeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GradesViewModel(application: Application) : AndroidViewModel(application) {

    private val gradeDao = GradeDatabase.getDatabase(application).gradeDao()
    val allGrades: LiveData<List<Grade>> = gradeDao.getAllGrades()

    fun addGrade(grade: Grade) {
        viewModelScope.launch(Dispatchers.IO) {
            gradeDao.insert(grade)
        }
    }
}
