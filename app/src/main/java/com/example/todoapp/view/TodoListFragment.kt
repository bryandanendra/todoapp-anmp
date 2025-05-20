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
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        
        binding.btnFab.setOnClickListener {
            findNavController().navigate(R.id.actionCreateTodo)
        }
    }
    
    private fun setupRecyclerView() {
        todoAdapter = TodoAdapter(
            onTaskCheckedChange = { todo, isChecked ->
                viewModel.updateTodoStatus(todo.id, isChecked)
            },
            onTaskEdit = { todo ->
                // This is now handled by the EditClickListener in the adapter
            }
        )
        
        binding.recViewTodo.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 