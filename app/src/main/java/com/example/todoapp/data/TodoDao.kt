package com.example.todoapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)
    
    @Query("SELECT * FROM todo_table ORDER BY priority DESC, id ASC")
    fun getAllTodos(): Flow<List<Todo>>
    
    @Query("SELECT * FROM todo_table WHERE id = :id")
    suspend fun getTodoById(id: Int): Todo?
    
    @Delete
    suspend fun deleteTodo(todo: Todo)
    
    @Query("UPDATE todo_table SET isCompleted = :isCompleted WHERE id = :id")
    suspend fun updateTodoStatus(id: Int, isCompleted: Boolean)
    
    @Update
    suspend fun updateTodo(todo: Todo)
} 