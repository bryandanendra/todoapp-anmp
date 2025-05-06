package com.example.todoapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.data.Todo
import com.example.todoapp.databinding.FragmentCreateTodoBinding
import com.example.todoapp.viewmodel.TodoViewModel

class CreateTodoFragment : Fragment() {

    private var _binding: FragmentCreateTodoBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: TodoViewModel by viewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateTodoBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.btnAdd.setOnClickListener {
            saveTodo()
        }
    }
    
    private fun saveTodo() {
        val title = binding.txtTitle.text.toString().trim()
        val notes = binding.txtNotes.text.toString().trim()
        
        if (title.isEmpty()) {
            binding.txtTitle.error = "Title cannot be empty"
            return
        }
        
        val todo = Todo(
            title = title,
            description = notes
        )
        
        viewModel.insertTodo(todo)
        Toast.makeText(requireContext(), "Todo added successfully", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 