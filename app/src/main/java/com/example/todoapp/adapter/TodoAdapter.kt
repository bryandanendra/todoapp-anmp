package com.example.todoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.widget.CompoundButton
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.data.Todo
import com.example.todoapp.databinding.ItemTodoBinding
import com.example.todoapp.R
import com.example.todoapp.view.TodoCheckedChangeListener
import com.example.todoapp.view.TodoEditClickListener

class TodoAdapter(
    private val onTaskCheckedChange: (Todo, Boolean) -> Unit,
    private val onTaskEdit: (Todo) -> Unit
) : ListAdapter<Todo, TodoAdapter.TodoViewHolder>(TodoDiffCallback()), 
    TodoCheckedChangeListener, TodoEditClickListener {

    inner class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = getItem(position)
        holder.binding.todo = currentTodo
        holder.binding.listener = this
        holder.binding.editListener = this
        holder.binding.executePendingBindings()
    }

    override fun onCheckedChanged(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        onTaskCheckedChange(obj, isChecked)
    }

    override fun onTodoEditClick(v: View) {
        val todoId = v.tag.toString().toInt()
        val todo = currentList.find { it.id == todoId }
        todo?.let {
            onTaskEdit(it)
            val action = R.id.actionEditTodo
            val bundle = androidx.core.os.bundleOf("uuid" to it.id)
            Navigation.findNavController(v).navigate(action, bundle)
        }
    }
}

class TodoDiffCallback : DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }
} 