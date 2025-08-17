package com.example.androidmasterclass.modules.room_db.many_to_many.view_models

import com.example.androidlauncher.utils.Resource
import com.example.androidmasterclass.modules.room_db.many_to_many.db.ManyToManyRepository
import com.example.androidmasterclass.modules.room_db.many_to_many.models.DataCourse
import com.example.androidmasterclass.modules.room_db.many_to_many.models.DataStudent
import javax.inject.Inject

class ManyToManyUseCase @Inject constructor(private val repository: ManyToManyRepository) {
    suspend fun insertCourse(course: DataCourse): Resource<Unit> {
        return if(course.courseName.isEmpty())  Resource.Error("Empty Course Name")
        else repository.insertCourse(course)
    }

    suspend fun insertStudent(student: DataStudent): Resource<Unit> {
        return if(student.studentName.isEmpty())  Resource.Error("Empty Student Name")
        else repository.insertStudent(student)
    }

    fun getStudents() = repository.getStudents()
    fun getCourses() = repository.getCourses()
    fun getCourseWithStudents(id : Long) = repository.getCourseWithStudents(id)
    fun getStudentWithCourses(id: Long) = repository.getStudentWithCourses(id)
}