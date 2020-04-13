package com.joeparker.gradewiz.database.repository

import androidx.lifecycle.LiveData
import com.joeparker.gradewiz.database.dao.WordDao
import com.joeparker.gradewiz.database.entity.Word

class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getAllAlphabetised()

    suspend fun insertWord(word: Word) {
        wordDao.insert(word)
    }
}
