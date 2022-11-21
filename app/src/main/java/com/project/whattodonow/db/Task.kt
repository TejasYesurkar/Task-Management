package com.project.roomdbkotlin.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Task(val title: String,
                val description: String,
                val priority: Int,
                @PrimaryKey(autoGenerate = false) val id: Int? = null)

