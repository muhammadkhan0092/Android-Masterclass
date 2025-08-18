package com.example.androidmasterclass.modules.room_db.views.menu_setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidmasterclass.R
import com.example.androidmasterclass.databinding.RoomMainMenuFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomMainMenuFragment : Fragment(){
    private var _binding : RoomMainMenuFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RoomMainMenuFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickListeners()
    }

    private fun onClickListeners() {
        onOneToOneClicked()
        onManyToManyClicked()
    }

    private fun onManyToManyClicked() {
        binding.btnManyToMany.setOnClickListener {
            navigate(R.id.action_roomMainMenuFragment_to_roomManyToManyMenuFragment)
        }
    }
    private fun onOneToOneClicked() {
        binding.btnOneToOne.setOnClickListener {
            navigate(R.id.action_roomMainMenuFragment_to_roomOneToOneMenuFragment)
        }
    }

    private fun navigate(action : Int) {
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}