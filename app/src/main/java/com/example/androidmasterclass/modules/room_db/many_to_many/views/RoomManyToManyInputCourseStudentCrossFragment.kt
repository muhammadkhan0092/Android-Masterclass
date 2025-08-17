package com.example.androidmasterclass.modules.room_db.many_to_many.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidlauncher.utils.Resource
import com.example.androidmasterclass.databinding.RoomManyToManyInputCourseStudentCrossFragmentBinding
import com.example.androidmasterclass.modules.room_db.many_to_many.models.DataStudentCourseCross
import com.example.androidmasterclass.modules.room_db.many_to_many.view_models.RoomManyToManyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RoomManyToManyInputCourseStudentCrossFragment : Fragment(){
    private var _binding : RoomManyToManyInputCourseStudentCrossFragmentBinding? = null
    private val viewModel by viewModels<RoomManyToManyViewModel>()
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RoomManyToManyInputCourseStudentCrossFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickListener()
    }

    private fun onClickListener() {
        binding.btnSave.setOnClickListener {
            val studentId = binding.etStudentId.text.toString()
            val courseId = binding.etCourseId.text.toString()
            if(studentId.isEmpty() || courseId.isEmpty()){
                Toast.makeText(requireContext(), "Fill All Fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else
            {
                lifecycleScope.launch {
                    val response = viewModel.insertStudentCourseCross(DataStudentCourseCross(studentId.toLong(),courseId.toLong()))
                    handleResponse(response)
                }
            }
        }
    }

    private fun handleResponse(response: Resource<Unit>) {
        if(response.message!=null){
            Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(requireContext(), "Insertion Successfull", Toast.LENGTH_SHORT).show()
            binding.apply {
                etCourseId.text.clear()
                etStudentId.text.clear()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}