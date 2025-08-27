package com.example.androidmasterclass.modules.firebase_realtime.presentation.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlauncher.utils.Resource
import com.example.androidlauncher.utils.VerticalItemDecoration
import com.example.androidmasterclass.databinding.FirestoreDisplayFragmentBinding
import com.example.androidmasterclass.modules.firestore.presentation.adapter.FirestoreDisplayAdapter
import com.example.androidmasterclass.modules.firestore.presentation.view_models.FirestoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FirebaseDisplayFragment : Fragment(){
    private var _binding : FirestoreDisplayFragmentBinding? = null
    private val binding get() = _binding!!
    private val firestoreDisplayAdapter by lazy { FirestoreDisplayAdapter() }
    private val viewModel by activityViewModels<FirestoreViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FirestoreDisplayFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUpdateRv()
        observeUsers()
        onDeleteClicked()
    }

    private fun onDeleteClicked() {
        firestoreDisplayAdapter.onClick = {
            binding.progressBar.visibility = View.VISIBLE
            lifecycleScope.launch {
                val response = viewModel.deleteUser(it.email)
                binding.progressBar.visibility = View.GONE
                when(response){
                    is Resource.Error<*> -> Toast.makeText(requireContext(), response.message!!, Toast.LENGTH_SHORT).show()
                    is Resource.Loading<*> -> {}
                    is Resource.Success<*> -> Toast.makeText(requireContext(), "Deletion Successfull", Toast.LENGTH_SHORT).show()
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
                    is Resource.Success<*> -> firestoreDisplayAdapter.differ.submitList(it.data!!)
                    is Resource.Unspecified<*> -> {}
                }
            }
        }
    }


    private fun setupUpdateRv() {
        binding.displayRv.apply {
            adapter = firestoreDisplayAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            addItemDecoration(VerticalItemDecoration(30))
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}