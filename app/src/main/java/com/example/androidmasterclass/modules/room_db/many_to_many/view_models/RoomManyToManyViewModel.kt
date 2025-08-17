package com.example.androidmasterclass.modules.room_db.many_to_many.view_models

import androidx.lifecycle.ViewModel
import com.example.androidmasterclass.modules.room_db.many_to_many.models.DataCourse
import com.example.androidmasterclass.modules.room_db.many_to_many.models.DataStudent
import com.example.androidmasterclass.modules.room_db.many_to_many.models.DataStudentCourseCross
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoomManyToManyViewModel @Inject constructor(private val useCase: ManyToManyUseCase) : ViewModel(){
    suspend fun insertStudent(dataStudent: DataStudent) = useCase.insertStudent(dataStudent)
    suspend fun insertCourse(dataCourse: DataCourse) = useCase.insertCourse(dataCourse)
    suspend fun insertStudentCourseCross(data : DataStudentCourseCross) = useCase.insertStudentCourseCross(data)
    fun getCourses() = useCase.getCourses()
    fun getStudents() = useCase.getStudents()
    fun getCourseWithStudents(id : Long) = useCase.getCourseWithStudents(id)
    fun getStudentWithCourses(id: Long) = useCase.getStudentWithCourses(id)
}