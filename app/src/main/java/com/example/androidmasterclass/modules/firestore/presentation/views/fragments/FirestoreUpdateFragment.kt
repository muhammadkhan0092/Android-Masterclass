package com.example.androidmasterclass.modules.firestore.presentation.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidmasterclass.databinding.FirestoreUpdateFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirestoreUpdateFragment : Fragment(){
    private var _binding : FirestoreUpdateFragmentBinding? = null
    private val binding get() = _binding!!


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
        onClickListeners()
    }

    private fun onClickListeners() {

    }


    private fun navigate(action : Int) {
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}