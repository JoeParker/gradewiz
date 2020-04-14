package com.joeparker.gradewiz.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joeparker.gradewiz.database.entity.Grade

@Dao
interface GradeDao {

    @Query("SELECT * FROM grades ORDER BY name ASC")
    fun getAllAlphabetised(): LiveData<List<Grade>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(grade: Grade)

    @Query("DELETE FROM grades")
    suspend fun deleteAll()
}
