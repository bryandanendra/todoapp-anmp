package com.example.todoapp.view

import android.view.View
import android.widget.CompoundButton
import com.example.todoapp.data.Todo

interface TodoCheckedChangeListener {
    fun onCheckedChanged(cb: CompoundButton, isChecked: Boolean, obj: Todo)
}

interface TodoEditClickListener {
    fun onTodoEditClick(v: View)
}

interface RadioClickListener {
    fun onRadioClick(v: View, priority: Int, obj: Todo)
}

interface TodoSaveChangesListener {
    fun onTodoSaveChangesClick(v: View, obj: Todo)
} 