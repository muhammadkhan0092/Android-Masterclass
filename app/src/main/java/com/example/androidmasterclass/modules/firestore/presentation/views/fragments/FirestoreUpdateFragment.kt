package com.example.androidmasterclass.modules.firestore.presentation.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlauncher.utils.VerticalItemDecoration
import com.example.androidmasterclass.databinding.FirestoreUpdateFragmentBinding
import com.example.androidmasterclass.modules.firestore.presentation.adapter.FirestoreUpdateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirestoreUpdateFragment : Fragment(){
    private var _binding : FirestoreUpdateFragmentBinding? = null
    private val binding get() = _binding!!
    private val firestoreUpdateAdapter by lazy { FirestoreUpdateAdapter() }


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
    }

    private fun onUpdateClicked() {
        firestoreUpdateAdapter.onClick = {

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