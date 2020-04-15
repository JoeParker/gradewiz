package com.joeparker.gradewiz.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.joeparker.gradewiz.database.dao.GradeDao
import com.joeparker.gradewiz.database.dao.ModuleDao
import com.joeparker.gradewiz.database.entity.Grade
import com.joeparker.gradewiz.database.entity.Module
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [
        Grade::class,
        Module::class
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gradeDao(): GradeDao
    abstract fun moduleDao(): ModuleDao

    companion object {
        // Singleton
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database"
                )
                .fallbackToDestructiveMigration()
                .addCallback(AppDatabaseCallback(scope))
                .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class AppDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.gradeDao(), database.moduleDao())
                }
            }
        }

        suspend fun populateDatabase(gradeDao: GradeDao, moduleDao: ModuleDao) {
            gradeDao.deleteAll()
            moduleDao.deleteAll()

            // Add sample modules...
            moduleDao.insert(Module(code = "CSC101", name = "Computer Programming"))

            // Add sample grades...
            gradeDao.insert(Grade(name = "Coursework Assignment 1", mark = 62.0f, weighting = 25.0f))
            gradeDao.insert(Grade(name = "Coursework Assignment 2", mark = 71.5f, weighting = 25.0f))
            gradeDao.insert(Grade(name = "Exam", mark = 58.0f, weighting = 50.0f))
        }
    }

}

