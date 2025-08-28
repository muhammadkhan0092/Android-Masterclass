package com.example.androidmasterclass.modules.firebase_realtime.presentation.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidmasterclass.utils.VerticalItemDecoration
import com.example.androidmasterclass.R
import com.example.androidmasterclass.databinding.FirestoreMainMenuFragmentBinding
import com.example.androidmasterclass.common.presentation.adapters.MainMenuAdapter
import com.example.androidmasterclass.common.presentation.models.DataMainMenu
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirebaseMainMenuFragment : Fragment(){
    private var _binding : FirestoreMainMenuFragmentBinding? = null
    private val binding get() = _binding!!
    private val mainMenuAdapter : MainMenuAdapter by lazy { MainMenuAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FirestoreMainMenuFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFirestoreRv()
        onClickListeners()
    }


    private fun onClickListeners() {
        onItemClick()
    }

    private fun onItemClick() {
        mainMenuAdapter.onClick = {
            when(it){
                getString(R.string.insert)-> navigate(R.id.action_firebaseMainMenuFragment_to_firebaseInsertFragment)
                getString(R.string.display)->navigate(R.id.action_firebaseMainMenuFragment_to_firebaseDisplayFragment)
                else -> navigate(R.id.action_firebaseMainMenuFragment_to_firebaseUpdateFragment)
            }
        }
    }


    private fun navigate(action : Int) {
        findNavController().navigate(action)
    }

    private fun setupFirestoreRv() {
        binding.rvFirestore.apply {
            adapter = mainMenuAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            addItemDecoration(VerticalItemDecoration(25))
        }
        mainMenuAdapter.differ.submitList(returnMenuItems())
    }

    private fun returnMenuItems(): MutableList<DataMainMenu>? {
        return mutableListOf(
            DataMainMenu(getString(R.string.insert)),
            DataMainMenu(getString(R.string.update)),
            DataMainMenu(getString(R.string.display))
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}