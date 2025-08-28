package com.example.androidmasterclass.modules.firebase_realtime.presentation.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlauncher.utils.Resource
import com.example.androidlauncher.utils.VerticalItemDecoration
import com.example.androidmasterclass.databinding.FirestoreUpdateFragmentBinding
import com.example.androidmasterclass.modules.firebase_realtime.presentation.view_models.FirebaseViewModel
import com.example.androidmasterclass.modules.firestore.presentation.adapter.FirestoreUpdateAdapter
import com.example.androidmasterclass.modules.firestore.presentation.view_models.FirestoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FirebaseUpdateFragment : Fragment(){
    private var _binding : FirestoreUpdateFragmentBinding? = null
    private val binding get() = _binding!!
    private val firestoreUpdateAdapter by lazy { FirestoreUpdateAdapter() }
    private val viewModel by viewModels<FirebaseViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FirestoreUpdateFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUpdateRv()
        onUpdateClicked()
        observeUsers()
    }

    private fun onUpdateClicked() {
        firestoreUpdateAdapter.onClick = {
            binding.progressBar.visibility = View.VISIBLE
            lifecycleScope.launch {
                val result = viewModel.updateUser(it)
                binding.progressBar.visibility = View.GONE
                when(result){
                    is Resource.Error<*> -> Toast.makeText(requireContext(), result.message!!, Toast.LENGTH_SHORT).show()
                    is Resource.Loading<*> -> {}
                    is Resource.Success<*> -> {
                        Toast.makeText(requireContext(), "Update Successfull", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Unspecified<*> -> {}
                }
            }
        }
    }

    private fun observeUsers() {
        lifecycleScope.launch {
            viewModel.getUsers().collectLatest {
                binding.progressBar.visibility = View.GONE
                when(it){
                    is Resource.Error<*> -> Toast.makeText(requireContext(), it.message!!, Toast.LENGTH_SHORT).show()
                    is Resource.Loading<*> -> Toast.makeText(requireContext(), "Fetching Data", Toast.LENGTH_SHORT).show()
                    is Resource.Success<*> -> firestoreUpdateAdapter.differ.submitList(it.data!!)
                    is Resource.Unspecified<*> -> {}
                }
            }
        }
    }
    private fun setupUpdateRv() {
        binding.updateRv.apply {
            adapter = firestoreUpdateAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            addItemDecoration(VerticalItemDecoration(30))
        }
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}