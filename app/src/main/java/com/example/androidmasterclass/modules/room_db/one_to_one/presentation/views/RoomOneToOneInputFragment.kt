package com.example.androidmasterclass.modules.room_db.one_to_one.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidmasterclass.databinding.RoomOneToOneInputFragmentBinding
import com.example.androidmasterclass.modules.room_db.one_to_one.domain.models.DataAddress
import com.example.androidmasterclass.modules.room_db.one_to_one.domain.models.DataUser
import com.example.androidmasterclass.modules.room_db.one_to_one.presentation.view_models.OneToOneViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RoomOneToOneInputFragment : Fragment(){
    private var _binding : RoomOneToOneInputFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<OneToOneViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RoomOneToOneInputFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onSaveClickedListener()
    }

    private fun onSaveClickedListener() {
        binding.apply {
            btnSave.setOnClickListener {
                val userName = etUserName.text.toString()
                val userEmail = etUserEmail.text.toString()
                val city = etCity.text.toString()
                if(userEmail.isEmpty() || userName.isEmpty() || city.isEmpty()){
                    Toast.makeText(requireContext(), "Fill The Details", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                {
                    val userData = DataUser(0,userName,userEmail)
                    val addressData = DataAddress(0,city,0)
                    insertAndObserveData(userData,addressData)
                }
            }
        }
    }

    private fun insertAndObserveData(
        user: DataUser,
        address: DataAddress
    ) {
        lifecycleScope.launch {
            val response = viewModel.insertUserWithAddress(user,address)
            if(response.data==null){
                Toast.makeText(requireContext(),"Data Insertion Failed", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(requireContext(), "Data Added Successfully", Toast.LENGTH_SHORT).show()
                clearEditTexts()
            }
        }
    }
    private fun clearEditTexts(){
        val ets = listOf(binding.etCity,binding.etUserName,binding.etUserEmail)
        ets.forEach {
            it.text.clear()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}