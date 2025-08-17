package com.example.androidmasterclass.modules.room_db.one_to_one.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.androidmasterclass.R
import com.example.androidmasterclass.databinding.RoomOneToOneMenuFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomOneToOneMenuFragment : Fragment(){
    private var _binding : RoomOneToOneMenuFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RoomOneToOneMenuFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickListeners()
    }

    private fun onClickListeners() {
        onInputClicked()
        onDisplayClicked()
    }

    private fun onDisplayClicked() {
        binding.btnDisplay.setOnClickListener {
            val action = R.id.action_roomOneToOneMenuFragment_to_roomOneToOneDisplayFragment
            findNavController().navigate(action)
        }
    }

    private fun onInputClicked() {
        binding.btnInput.setOnClickListener {
            val action = R.id.action_roomOneToOneMenuFragment_to_roomOneToOneInputFragment
            findNavController().navigate(action)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}