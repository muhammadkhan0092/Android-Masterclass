package com.example.androidmasterclass.modules.room_db.menu_setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.androidmasterclass.R
import com.example.androidmasterclass.databinding.RoomInputOutputMenuFragmentBinding
import com.example.androidmasterclass.databinding.RoomMainMenuFragmentBinding

class RoomInputOutputMenuFragment : Fragment(){
    private var _binding : RoomInputOutputMenuFragmentBinding? = null
    private val navArgs by navArgs<RoomInputOutputMenuFragmentArgs>()
    private lateinit var type : String
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RoomInputOutputMenuFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getType()
        onClickListeners()
    }

    private fun onClickListeners() {
        onInputClicked()
        onDisplayClicked()
    }

    private fun onDisplayClicked() {
        binding.btnDisplay.setOnClickListener {
            val action = when(type){
                getString(R.string.one_to_one)-> R.id.action_roomInputOutputMenuFragment_to_roomOneToOneDisplayFragment
                getString(R.string.one_to_many)-> R.id.action_roomInputOutputMenuFragment_to_roomOneToManyDisplayFragment
                else -> R.id.action_roomInputOutputMenuFragment_to_roomManyToManyDisplayFragment
            }
            findNavController().navigate(action)
        }
    }

    private fun onInputClicked() {
        binding.btnInput.setOnClickListener {
            val action = when(type){
                getString(R.string.one_to_one)-> R.id.action_roomInputOutputMenuFragment_to_roomOneToOneInputFragment
                getString(R.string.one_to_many)-> R.id.action_roomInputOutputMenuFragment_to_roomOneToManyInputFragment
                else -> R.id.action_roomInputOutputMenuFragment_to_roomManyToManyInputFragment
            }
            findNavController().navigate(action)
        }
    }

    private fun getType() {
        type = navArgs.type
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}