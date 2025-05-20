package com.example.todoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String,
    var description: String,
    var isCompleted: Boolean = false,
    var priority: Int = 1, // 1: Low, 2: Medium, 3: High
    val createdDate: Long = System.currentTimeMillis()
) 