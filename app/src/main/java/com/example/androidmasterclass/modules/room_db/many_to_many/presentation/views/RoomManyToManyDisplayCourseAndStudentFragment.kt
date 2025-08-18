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
import com.example.androidlauncher.utils.Resource
import com.example.androidmasterclass.databinding.RoomManyToManyDisplayCourseAndStudentFragmentBinding
import com.example.androidmasterclass.modules.room_db.many_to_many.presentation.common.Constants.TYPE_COURSE
import com.example.androidmasterclass.modules.room_db.many_to_many.domain.models.DataCourse
import com.example.androidmasterclass.modules.room_db.many_to_many.domain.models.DataStudent
import com.example.androidmasterclass.modules.room_db.many_to_many.presentation.view_models.RoomManyToManyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RoomManyToManyDisplayCourseAndStudentFragment : Fragment(){
    private var _binding : RoomManyToManyDisplayCourseAndStudentFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<RoomManyToManyViewModel>()
    private val navArgs by navArgs<RoomManyToManyDisplayCourseAndStudentFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RoomManyToManyDisplayCourseAndStudentFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAndSetData()
    }

    private fun getAndSetData() {
        val navData = navArgs.type
        val flow = if(navData==TYPE_COURSE){
            observeCourse()
        }
        else
        {
            observeStudents()
        }
    }

    private fun observeStudents() {
        lifecycleScope.launch {
            viewModel.getStudents().collectLatest {
                when(it){
                    is Resource.Error<*> -> Toast.makeText(requireContext(), "Error ${it.message}", Toast.LENGTH_SHORT).show()
                    is Resource.Loading<*> -> Toast.makeText(requireContext(), "Fetching Data", Toast.LENGTH_SHORT).show()
                    is Resource.Success<*> -> setStudentData(it.data!!)
                    is Resource.Unspecified<*> -> {}
                }
            }
        }
    }

    private fun observeCourse() {
        lifecycleScope.launch {
            viewModel.getCourses().collectLatest {
                when(it){
                    is Resource.Error<*> -> Toast.makeText(requireContext(), "Error ${it.message}", Toast.LENGTH_SHORT).show()
                    is Resource.Loading<*> -> Toast.makeText(requireContext(), "Fetching Data", Toast.LENGTH_SHORT).show()
                    is Resource.Success<*> -> setCourseData(it.data!!)
                    is Resource.Unspecified<*> -> {}
                }
            }
        }
    }
    private fun setStudentData(data: List<DataStudent>) {
        var text = ""
        data.forEach {
            text+=it.studentId.toString() + "      " + it.studentName + "\n"
        }
        binding.tvDetails.text = text.toString()
    }

    private fun setCourseData(data: List<DataCourse>) {
        var text = ""
        data.forEach {
            text+=it.courseId.toString() + "      " + it.courseName + "\n"
        }
        binding.tvDetails.text = text.toString()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}