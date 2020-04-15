package com.joeparker.gradewiz.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joeparker.gradewiz.database.entity.Module

@Dao
interface ModuleDao {

    @Query("SELECT * FROM modules ORDER BY code ASC")
    fun getAll(): LiveData<List<Module>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(module: Module)

    @Query("DELETE FROM modules")
    suspend fun deleteAll()
}
