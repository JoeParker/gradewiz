package com.joeparker.gradewiz

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.joeparker.gradewiz.database.AppDatabase
import com.joeparker.gradewiz.database.entity.Word
import com.joeparker.gradewiz.database.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WordRepository
    val allWords: LiveData<List<Word>>

    init {
        repository = WordRepository(AppDatabase.getDatabase(application, viewModelScope).wordDao())
        allWords = repository.allWords
    }

    // Non-blocking
    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertWord(word)
    }
}
