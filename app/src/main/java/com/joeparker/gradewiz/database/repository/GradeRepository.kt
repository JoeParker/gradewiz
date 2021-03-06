package com.joeparker.gradewiz.database.repository

import androidx.lifecycle.LiveData
import com.joeparker.gradewiz.database.dao.GradeDao
import com.joeparker.gradewiz.database.entity.Grade

class GradeRepository(private val gradeDao: GradeDao) {

    val allGrades: LiveData<List<Grade>> = gradeDao.getAll()
    val total: LiveData<Float> = gradeDao.getTotal()

    suspend fun insertGrade(grade: Grade) {
        gradeDao.insert(grade)
    }

    suspend fun deleteGrade(grade: Grade) {
        gradeDao.delete(grade)
    }

    suspend fun deleteAll() {
        gradeDao.deleteAll()
    }
}
