package com.example.androidmasterclass.modules.room_db.many_to_many.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CourseWithStudents(
    @Embedded
    val course : DataCourse,
    @Relation(
        parentColumn = "courseId",
        entityColumn = "studentId",
        associateBy = Junction(DataStudentCourseCross::class)
    )
    val students : List<DataStudent>
)
