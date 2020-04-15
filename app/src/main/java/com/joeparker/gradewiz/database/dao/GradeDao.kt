package com.joeparker.gradewiz.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.joeparker.gradewiz.database.entity.Grade

@Dao
interface GradeDao {

    @Query("SELECT * FROM grades")
    fun getAll(): LiveData<List<Grade>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(grade: Grade)

    @Query("DELETE FROM grades")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(grade: Grade)
}
