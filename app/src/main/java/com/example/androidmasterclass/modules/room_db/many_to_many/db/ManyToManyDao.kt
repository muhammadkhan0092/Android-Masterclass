package com.example.androidmasterclass.modules.room_db.many_to_many.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.androidmasterclass.modules.room_db.many_to_many.models.CourseWithStudents
import com.example.androidmasterclass.modules.room_db.many_to_many.models.DataCourse
import com.example.androidmasterclass.modules.room_db.many_to_many.models.DataStudent
import com.example.androidmasterclass.modules.room_db.many_to_many.models.DataStudentCourseCross
import com.example.androidmasterclass.modules.room_db.many_to_many.models.StudentWithCourses
import kotlinx.coroutines.flow.Flow

@Dao
interface ManyToManyDao {
    @Insert
    suspend fun insertStudent(dataStudent: DataStudent)

    @Insert
    suspend fun insertCourse(dataCourse: DataCourse)

    @Query("SELECT * FROM DataStudent")
    fun getStudents() : Flow<List<DataStudent>>

    @Query("SELECT * FROM DataCourse")
    fun getCourses() : Flow<List<DataCourse>>

    @Insert
    suspend fun insertStudentCourseCross(dataStudentCourseCross: DataStudentCourseCross)

    @Transaction
    @Query("SELECT * FROM DataStudent WHERE studentId=:studentId")
    fun getStudentWithCourses(studentId : Long) : Flow<StudentWithCourses?>

    @Transaction
    @Query("SELECT * FROM DataCourse WHERE courseId=:id")
    fun getCourseWithStudents(id : Long) : Flow<CourseWithStudents?>
}