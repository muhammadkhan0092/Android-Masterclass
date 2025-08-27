package com.example.androidmasterclass.modules.firebase_realtime.presentation.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.androidmasterclass.databinding.FirestoreInsertFragmentBinding
import com.example.androidmasterclass.modules.firestore.domain.models.DataUser
import com.example.androidmasterclass.modules.firestore.presentation.view_models.FirestoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FirebaseInsertFragment : Fragment(){
    private var _binding : FirestoreInsertFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<FirestoreViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FirestoreInsertFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickListeners()
    }

    private fun onClickListeners() {
        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            insertAndObserveResult(DataUser(email,name))
        }
    }

    private fun insertAndObserveResult(user: DataUser){
        lifecycleScope.launch {
            val response = viewModel.insertUser(user)
            if(response.message!=null){
                Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(requireContext(), "Data Inserted Successfully", Toast.LENGTH_SHORT).show()
            }
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