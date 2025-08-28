package com.example.androidmasterclass.modules.room_db.many_to_many.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidmasterclass.utils.Resource
import com.example.androidmasterclass.databinding.RoomManyToManyDisplayCourseStudentCrossFragmentBinding
import com.example.androidmasterclass.modules.room_db.many_to_many.domain.models.CourseWithStudents
import com.example.androidmasterclass.modules.room_db.many_to_many.domain.models.StudentWithCourses
import com.example.androidmasterclass.modules.room_db.many_to_many.presentation.view_models.RoomManyToManyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RoomManyToManyDisplayCourseStudentCrossFragment : Fragment(){
    private var _binding : RoomManyToManyDisplayCourseStudentCrossFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<RoomManyToManyViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RoomManyToManyDisplayCourseStudentCrossFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickListeners()
    }

    private fun onClickListeners() {
        onCourseSearched()
        onStudentSearched()
    }

    private fun onCourseSearched() {
        binding.btnCourse.setOnClickListener {
            val id = binding.etId.text.toString()
            if(validateInput(id)){
                val response = viewModel.getCourseWithStudents(id.toLong())
                handleCourseResponse(response)
            }
        }
    }

    private fun onStudentSearched() {
        binding.btnStudent.setOnClickListener {
            val id = binding.etId.text.toString()
            if(validateInput(id)){
                val response = viewModel.getStudentWithCourses(id.toLong())
                handleStudentResponse(response)
            }
        }
    }

    private fun handleStudentResponse(response: Flow<Resource<StudentWithCourses>>) {
        lifecycleScope.launch {
            response.collectLatest {
                when(it){
                    is Resource.Error<*> -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    is Resource.Loading<*> -> Toast.makeText(requireContext(), "Loading Data", Toast.LENGTH_SHORT).show()
                    is Resource.Success<*> -> displayStudentOutput(it.data!!)
                    is Resource.Unspecified<*> -> {}
                }
            }
        }
    }

    private fun handleCourseResponse(response: Flow<Resource<CourseWithStudents>>) {
        lifecycleScope.launch {
            response.collectLatest {
                when(it){
                    is Resource.Error<*> -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    is Resource.Loading<*> -> Toast.makeText(requireContext(), "Loading Data", Toast.LENGTH_SHORT).show()
                    is Resource.Success<*> -> displayCourseOutput(it.data!!)
                    is Resource.Unspecified<*> -> {}
                }
            }
        }
    }

    private fun displayCourseOutput(data: CourseWithStudents) {
        binding.tvHeading.text = data.course.courseName
        var text = ""
        data.students.forEach {
            text+= it.studentName + "\n"
        }
        binding.tvDetails.text = text
    }
    private fun displayStudentOutput(data: StudentWithCourses) {
        binding.tvHeading.text = data.student.studentName
        var text = ""
        data.courses.forEach {
            text+= it.courseName + "\n"
        }
        binding.tvDetails.text = text
    }

    private fun validateInput(id : String): Boolean {
        return if(id.isEmpty()){
            Toast.makeText(requireContext(), "Enter Id", Toast.LENGTH_SHORT).show()
            false
        }
        else
        {
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}