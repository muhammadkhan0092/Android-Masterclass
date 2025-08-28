package com.example.androidmasterclass.modules.room_db.many_to_many.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.androidmasterclass.utils.Resource
import com.example.androidmasterclass.R
import com.example.androidmasterclass.databinding.RoomManyToManyInputCourseAndStudentFragmentBinding
import com.example.androidmasterclass.modules.room_db.many_to_many.presentation.common.Constants.TYPE_COURSE
import com.example.androidmasterclass.modules.room_db.many_to_many.domain.models.DataCourse
import com.example.androidmasterclass.modules.room_db.many_to_many.domain.models.DataStudent
import com.example.androidmasterclass.modules.room_db.many_to_many.presentation.view_models.RoomManyToManyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RoomManyToManyInputCourseAndStudentFragment : Fragment(){
    private var _binding : RoomManyToManyInputCourseAndStudentFragmentBinding? = null
    private val binding get() = _binding!!
    private val navArgs by navArgs<RoomManyToManyInputCourseAndStudentFragmentArgs>()
    private lateinit var type : String
    private val viewModel by viewModels<RoomManyToManyViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RoomManyToManyInputCourseAndStudentFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getType()
        onClickListener()
    }

    private fun getType() {
        type = navArgs.type
        if(type==TYPE_COURSE){
            binding.etName.hint = getString(R.string.hint_course)
        }
        else
        {
            binding.etName.hint = getString(R.string.hint_student)
        }
    }

    private fun onClickListener() {
        binding.btnInsert.setOnClickListener {
            val data = binding.etName.text.toString()
            if(type==TYPE_COURSE){
                lifecycleScope.launch {
                    val response = viewModel.insertCourse(DataCourse(0,data))
                    handleResponse(response)

                }
            }
            else
            {
                lifecycleScope.launch {
                    val response = viewModel.insertStudent(DataStudent(0,data))
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
            binding.etName.text.clear()
            Toast.makeText(requireContext(), "Data Insertion Successfull", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}