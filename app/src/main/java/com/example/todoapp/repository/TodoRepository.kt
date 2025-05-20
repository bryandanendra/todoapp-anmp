package com.example.todoapp.repository

import com.example.todoapp.data.Todo
import com.example.todoapp.data.TodoDao
import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: TodoDao) {
    
    val allTodos: Flow<List<Todo>> = todoDao.getAllTodos()
    
    suspend fun insertTodo(todo: Todo) {
        todoDao.insertTodo(todo)
    }
    
    suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
    }
    
    suspend fun updateTodoStatus(id: Int, isCompleted: Boolean) {
        todoDao.updateTodoStatus(id, isCompleted)
    }
    
    suspend fun getTodoById(id: Int): Todo? {
        return todoDao.getTodoById(id)
    }
    
    suspend fun updateTodo(todo: Todo) {
        todoDao.updateTodo(todo)
    }
} 