package com.example.androidmasterclass.modules.room_db.many_to_many.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class StudentWithCourses(
    @Embedded
    val student : DataStudent,
    @Relation(
        parentColumn = "studentId",
        entityColumn = "courseId",
        associateBy = Junction(DataStudentCourseCross::class)
    )
    val courses : List<DataCourse>
)
