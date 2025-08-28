package com.example.androidmasterclass.modules.room_db.one_to_one.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidmasterclass.utils.Resource
import com.example.androidmasterclass.utils.VerticalItemDecoration
import com.example.androidmasterclass.databinding.RoomOneToOneDisplayFragmentBinding
import com.example.androidmasterclass.modules.room_db.one_to_one.presentation.adapters.UserAdapter
import com.example.androidmasterclass.modules.room_db.one_to_one.presentation.view_models.OneToOneViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RoomOneToOneDisplayFragment: Fragment(){
    private var _binding : RoomOneToOneDisplayFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<OneToOneViewModel>()
    private val usersAdapter : UserAdapter by lazy { UserAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RoomOneToOneDisplayFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUsersAdapter()
        observeUsersWithAddress()
    }

    private fun setupUsersAdapter() {
        binding.rvUsers.apply {
            adapter = usersAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            addItemDecoration(VerticalItemDecoration(25))
        }
    }
    private fun observeUsersWithAddress() {
        lifecycleScope.launch {
            viewModel.getUsersWithAddress().collectLatest {
                when(it){
                    is Resource.Error<*> -> Toast.makeText(requireContext(), "Error : ${it.message}", Toast.LENGTH_SHORT).show()
                    is Resource.Loading<*> -> Toast.makeText(requireContext(), "Loading ...", Toast.LENGTH_SHORT).show()
                    is Resource.Success<*> -> usersAdapter.differ.submitList(it.data!!)
                    is Resource.Unspecified<*> -> {}
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}