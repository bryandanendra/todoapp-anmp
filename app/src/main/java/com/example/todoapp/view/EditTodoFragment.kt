package com.example.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.R
import com.example.todoapp.data.Todo
import com.example.todoapp.databinding.FragmentEditTodoBinding
import com.example.todoapp.viewmodel.DetailTodoViewModel
import com.example.todoapp.viewmodel.TodoViewModel

class EditTodoFragment : Fragment(), RadioClickListener, TodoSaveChangesListener {
    private lateinit var binding: FragmentEditTodoBinding
    private val todoViewModel: TodoViewModel by viewModels()
    private var todoId = 0
    private lateinit var currentTodo: Todo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        todoId = arguments?.getInt("uuid") ?: 0
        
        binding.radioListener = this
        binding.saveListener = this
        
        todoViewModel.getTodoById(todoId).observe(viewLifecycleOwner) { todo ->
            todo?.let {
                currentTodo = it
                binding.todo = it
                updateRadioButtonState(it.priority)
            }
        }
    }

    private fun updateRadioButtonState(priority: Int) {
        when (priority) {
            1 -> binding.radioLowPriority.isChecked = true
            2 -> binding.radioMediumPriority.isChecked = true
            3 -> binding.radioHighPriority.isChecked = true
        }
    }
    
    override fun onRadioClick(v: View, priority: Int, obj: Todo) {
        obj.priority = priority
    }
    
    override fun onTodoSaveChangesClick(v: View, obj: Todo) {
        if (obj.title.isNotEmpty()) {
            todoViewModel.updateTodo(obj)
            Toast.makeText(requireContext(), "Todo updated", Toast.LENGTH_SHORT).show()
            requireActivity().onBackPressed()
        } else {
            Toast.makeText(requireContext(), "Title cannot be empty", Toast.LENGTH_SHORT).show()
        }
    }
}
