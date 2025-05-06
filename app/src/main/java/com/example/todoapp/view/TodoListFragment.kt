package com.example.todoapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.adapter.TodoAdapter
import com.example.todoapp.data.Todo
import com.example.todoapp.databinding.FragmentTodoListBinding
import com.example.todoapp.viewmodel.TodoViewModel

class TodoListFragment : Fragment() {

    private var _binding: FragmentTodoListBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: TodoViewModel by viewModels()
    private lateinit var todoAdapter: TodoAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoListBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        observeViewModel()
        
        binding.btnFab.setOnClickListener {
            findNavController().navigate(R.id.action_todoListFragment_to_createTodoFragment)
        }
    }
    
    private fun setupRecyclerView() {
        todoAdapter = TodoAdapter(
            onTaskCheckedChange = { todo, isChecked ->
                viewModel.updateTodoStatus(todo.id, isChecked)
            },
            onTaskEdit = { todo ->
                // Navigate to edit todo (can be implemented later)
            }
        )
        
        binding.recViewTodo.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
    
    private fun observeViewModel() {
        viewModel.allTodos.observe(viewLifecycleOwner) { todos ->
            todoAdapter.submitList(todos)
            
            if (todos.isEmpty()) {
                binding.txtError.visibility = View.VISIBLE
                binding.recViewTodo.visibility = View.GONE
            } else {
                binding.txtError.visibility = View.GONE
                binding.recViewTodo.visibility = View.VISIBLE
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 