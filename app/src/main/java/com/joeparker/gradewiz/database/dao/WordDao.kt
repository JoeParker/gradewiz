package com.joeparker.gradewiz.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joeparker.gradewiz.database.entity.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM words ORDER BY word ASC")
    fun getAllAlphabetised(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM words")
    suspend fun deleteAll()
}
