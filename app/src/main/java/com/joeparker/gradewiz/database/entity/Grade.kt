package com.joeparker.gradewiz.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grades")

data class Grade(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "note")
    val note: String?,
    @ColumnInfo(name = "mark")
    val mark: Float,
    @ColumnInfo(name = "weighting")
    val weighting: Float
)