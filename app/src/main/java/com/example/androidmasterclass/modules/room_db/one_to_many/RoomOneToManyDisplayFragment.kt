package com.example.androidmasterclass.modules.room_db.one_to_many

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidmasterclass.databinding.RoomOneToManyDisplayFragmentBinding
import com.example.androidmasterclass.databinding.RoomOneToOneDisplayFragmentBinding

class RoomOneToManyDisplayFragment : Fragment(){
    private var _binding : RoomOneToManyDisplayFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RoomOneToManyDisplayFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}