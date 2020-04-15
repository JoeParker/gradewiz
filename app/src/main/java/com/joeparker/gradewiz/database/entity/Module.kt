package com.joeparker.gradewiz.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "modules")

data class Module(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "code")
    val code: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "credits")
    val credits: Int
)