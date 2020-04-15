package com.joeparker.gradewiz

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.joeparker.gradewiz.database.AppDatabase
import com.joeparker.gradewiz.database.entity.Grade
import com.joeparker.gradewiz.database.repository.GradeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GradeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GradeRepository
    val allGrades: LiveData<List<Grade>>

    init {
        repository = GradeRepository(AppDatabase.getDatabase(application, viewModelScope).gradeDao())
        allGrades = repository.allGrades
    }

    // Non-blocking
    fun addGrade(grade: Grade) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertGrade(grade)
    }

    fun deleteGrade(grade: Grade) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteGrade(grade)
    }

    fun deleteAllGrades() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}
