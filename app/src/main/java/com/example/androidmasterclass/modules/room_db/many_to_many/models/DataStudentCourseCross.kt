package com.example.androidmasterclass.modules.room_db.many_to_many.models

import androidx.room.Entity
import androidx.room.ForeignKey


@Entity(
    tableName = "DataStudentCourseCross",
    primaryKeys = ["studentId","courseId"],
    foreignKeys = [
        ForeignKey(
            entity = DataStudent::class,
            parentColumns = ["studentId"],
            childColumns = ["studentId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DataCourse::class,
            parentColumns = ["courseId"],
            childColumns = ["courseId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DataStudentCourseCross(
    val studentId : Long,
    val courseId : Long
)
