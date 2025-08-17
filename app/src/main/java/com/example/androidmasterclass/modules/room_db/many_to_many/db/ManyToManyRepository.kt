package com.example.androidmasterclass.modules.room_db.many_to_many.db

import com.example.androidlauncher.utils.Resource
import com.example.androidmasterclass.modules.room_db.db.CustomRoomDatabase
import com.example.androidmasterclass.modules.room_db.many_to_many.models.CourseWithStudents
import com.example.androidmasterclass.modules.room_db.many_to_many.models.DataCourse
import com.example.androidmasterclass.modules.room_db.many_to_many.models.DataStudent
import com.example.androidmasterclass.modules.room_db.many_to_many.models.DataStudentCourseCross
import com.example.androidmasterclass.modules.room_db.many_to_many.models.StudentWithCourses
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.lang.Exception
import javax.inject.Inject

class ManyToManyRepository @Inject constructor(private val database: CustomRoomDatabase) {
    private val dao = database.getManyToManyDao()
    suspend fun insertStudent(student: DataStudent): Resource<Unit> {
        return try {
            dao.insertStudent(student)
            Resource.Success(Unit)
        }
        catch (e : Exception){
            Resource.Error(e.message.toString())
        }
    }
    suspend fun insertCourse(course: DataCourse): Resource<Unit> {
        return try {
            dao.insertCourse(course)
            Resource.Success(Unit)
        }
        catch (e : Exception){
            Resource.Error(e.message.toString())
        }
    }
    suspend fun insertStudentCourseCross(cross : DataStudentCourseCross): Resource<Unit> {
        return try {
            dao.insertStudentCourseCross(cross)
            Resource.Success(Unit)
        }
        catch (e : Exception){
            Resource.Error(e.message.toString())
        }
    }

    fun getStudentWithCourses(id: Long): Flow<Resource<StudentWithCourses>> =
        dao.getStudentWithCourses(id)
            .map { courseWithStudents ->
                if (courseWithStudents == null) {
                    Resource.Error("Student not found")
                } else {
                    Resource.Success(courseWithStudents)
                }
            }
            .onStart {
                emit(Resource.Loading())
            }
            .catch { e ->
                emit(Resource.Error(e.message ?: "Unknown error"))
            }

    fun getCourseWithStudents(id: Long): Flow<Resource<CourseWithStudents>> =
        dao.getCourseWithStudents(id)  // Flow<CourseWithStudents?>
            .map { courseWithStudents ->
                if (courseWithStudents == null) {
                    Resource.Error("Course not found")
                } else {
                    Resource.Success(courseWithStudents)
                }
            }
            .onStart {
                emit(Resource.Loading())
            }
            .catch { e ->
                emit(Resource.Error(e.message ?: "Unknown error"))
            }

}