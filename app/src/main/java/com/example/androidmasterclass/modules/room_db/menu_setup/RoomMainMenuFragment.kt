package com.example.androidmasterclass.modules.room_db.menu_setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidmasterclass.R
import com.example.androidmasterclass.databinding.RoomMainMenuFragmentBinding

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
        onOneToManyClicked()
        onManyToManyClicked()
    }

    private fun onManyToManyClicked() {
        binding.btnManyToMany.setOnClickListener {
            navigate(getString(R.string.many_to_many))
        }
    }

    private fun onOneToManyClicked() {
        binding.btnManyToMany.setOnClickListener {
            navigate(getString(R.string.one_to_many))
        }
    }
    private fun onOneToOneClicked() {
        binding.btnOneToOne.setOnClickListener {
            navigate(getString(R.string.one_to_many))
        }
    }

    private fun navigate(text : String) {
        val bundle = Bundle().also {
            it.putString("type",text)
        }
        findNavController().navigate(R.id.action_roomMainMenuFragment_to_roomInputOutputMenuFragment,bundle)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}