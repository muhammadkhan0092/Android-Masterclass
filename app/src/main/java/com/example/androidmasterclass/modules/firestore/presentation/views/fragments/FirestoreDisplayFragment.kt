package com.example.androidmasterclass.modules.firestore.presentation.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlauncher.utils.VerticalItemDecoration
import com.example.androidmasterclass.R
import com.example.androidmasterclass.databinding.FirestoreDisplayFragmentBinding
import com.example.androidmasterclass.databinding.RoomMainMenuFragmentBinding
import com.example.androidmasterclass.modules.firestore.presentation.adapter.FirestoreDisplayAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirestoreDisplayFragment : Fragment(){
    private var _binding : FirestoreDisplayFragmentBinding? = null
    private val binding get() = _binding!!
    private val firestoreDisplayAdapter by lazy { FirestoreDisplayAdapter() }


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
        onUpdateClicked()
    }

    private fun onUpdateClicked() {
        firestoreDisplayAdapter.onClick = {

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