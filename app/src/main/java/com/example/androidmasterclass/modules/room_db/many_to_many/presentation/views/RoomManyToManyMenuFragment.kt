package com.example.androidmasterclass.modules.room_db.many_to_many.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidmasterclass.R
import com.example.androidmasterclass.databinding.RoomManyToManyMenuFragmentBinding
import com.example.androidmasterclass.modules.room_db.many_to_many.presentation.common.Constants.TYPE_COURSE
import com.example.androidmasterclass.modules.room_db.many_to_many.presentation.common.Constants.TYPE_STUDENT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomManyToManyMenuFragment : Fragment(){
    private var _binding : RoomManyToManyMenuFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RoomManyToManyMenuFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickListeners()
    }

    private fun onClickListeners() {
        onInputCourse()
        onInputStudent()
        onDisplayCourse()
        onDisplayStudent()
        onInputCross()
        onDisplayCross()
    }

    private fun onDisplayCross() {
        binding.btnCrossDispaly.setOnClickListener {
            navigate(R.id.action_roomManyToManyMenuFragment_to_roomManyToManyDisplayCourseStudentCrossFragment)
        }
    }

    private fun onInputCross() {
        binding.btnInputCross.setOnClickListener {
            navigate(R.id.action_roomManyToManyMenuFragment_to_roomManyToManyInputCourseStudentCrossFragment)
        }
    }

    private fun onDisplayStudent() {
        binding.btnDisplayStudent.setOnClickListener {
            navigateWithText(TYPE_STUDENT,R.id.action_roomManyToManyMenuFragment_to_roomManyToManyDisplayCourseAndStudentFragment)
        }
    }

    private fun onDisplayCourse() {
        binding.btnDisplayCourse.setOnClickListener {
            navigateWithText(TYPE_COURSE,R.id.action_roomManyToManyMenuFragment_to_roomManyToManyDisplayCourseAndStudentFragment)
        }
    }

    private fun onInputStudent() {
        binding.btnInputStudent.setOnClickListener {
            navigateWithText(TYPE_STUDENT,R.id.action_roomManyToManyMenuFragment_to_roomManyToManyInputCourseAndStudentFragment)
        }
    }

    private fun onInputCourse() {
        binding.btnInputCourse.setOnClickListener {
            navigateWithText(TYPE_COURSE,R.id.action_roomManyToManyMenuFragment_to_roomManyToManyInputCourseAndStudentFragment)
        }
    }

    private fun navigateWithText(text : String,action: Int){
        val bundle = Bundle().also {
            it.putString("type",text)
        }
        findNavController().navigate(action,bundle)
    }

    private fun navigate(action : Int){
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}