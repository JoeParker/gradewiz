package com.joeparker.gradewiz.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.joeparker.gradewiz.database.dao.GradeDao
import com.joeparker.gradewiz.database.entity.Grade
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [
        Grade::class
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gradeDao(): GradeDao

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
                    populateDatabase(database.gradeDao())
                }
            }
        }

        suspend fun populateDatabase(gradeDao: GradeDao) {
            gradeDao.deleteAll()

            // Add sample grades...
        }
    }

}

