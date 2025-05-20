package com.example.todoapp.util

import android.graphics.Color
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.adapter.TodoAdapter
import com.example.todoapp.data.Todo

@BindingAdapter("todoList")
fun setTodoList(recyclerView: RecyclerView, todos: List<Todo>?) {
    todos?.let {
        val adapter = recyclerView.adapter as? TodoAdapter
        adapter?.submitList(it)
    }
}

@BindingAdapter("isSelectedPriority")
fun setSelectedPriority(radioButton: RadioButton, priority: Int) {
    when (radioButton.id) {
        com.example.todoapp.R.id.radioHigh -> radioButton.isChecked = priority == 3
        com.example.todoapp.R.id.radioMedium -> radioButton.isChecked = priority == 2
        com.example.todoapp.R.id.radioLow -> radioButton.isChecked = priority == 1
        com.example.todoapp.R.id.radioHighPriority -> radioButton.isChecked = priority == 3
        com.example.todoapp.R.id.radioMediumPriority -> radioButton.isChecked = priority == 2
        com.example.todoapp.R.id.radioLowPriority -> radioButton.isChecked = priority == 1
    }
}

@BindingAdapter("priorityColor")
fun setPriorityColor(view: View, priority: Int) {
    when (priority) {
        3 -> view.setBackgroundColor(Color.parseColor("#F44336")) // Red
        2 -> view.setBackgroundColor(Color.parseColor("#FF9800")) // Orange
        else -> view.setBackgroundColor(Color.parseColor("#4CAF50")) // Green
    }
}

@BindingAdapter("priorityText")
fun setPriorityText(textView: TextView, priority: Int) {
    when (priority) {
        3 -> {
            textView.text = "High Priority"
            textView.setTextColor(Color.parseColor("#F44336")) 
        }
        2 -> {
            textView.text = "Medium Priority"
            textView.setTextColor(Color.parseColor("#FF9800"))
        }
        else -> {
            textView.text = "Low Priority"
            textView.setTextColor(Color.parseColor("#4CAF50"))
        }
    }
} 