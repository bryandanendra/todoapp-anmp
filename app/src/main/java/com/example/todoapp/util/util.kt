package com.example.todoapp.util

import android.content.Context
import com.example.todoapp.data.TodoDao
import com.example.todoapp.data.TodoDatabase

const val db_name = "todo_database"

fun buildDb(context: Context): TodoDatabase {
    return TodoDatabase.getDatabase(context)
}