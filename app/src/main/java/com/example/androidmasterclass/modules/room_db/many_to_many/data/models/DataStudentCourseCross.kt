package com.example.androidmasterclass.modules.room_db.many_to_many.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.androidmasterclass.modules.room_db.many_to_many.domain.models.DataCourse
import com.example.androidmasterclass.modules.room_db.many_to_many.domain.models.DataStudent

@Entity(
    tableName = "DataStudentCourseCross",
    primaryKeys = ["studentId","courseId"],
    foreignKeys = [
        ForeignKey(
            entity = DataStudent::class,
            parentColumns = ["studentId"],
            childColumns = ["studentId"],
            onDelete = ForeignKey.Companion.CASCADE
        ),
        ForeignKey(
            entity = DataCourse::class,
            parentColumns = ["courseId"],
            childColumns = ["courseId"],
            onDelete = ForeignKey.Companion.CASCADE
        )
    ]
)
data class DataStudentCourseCross(
    val studentId : Long,
    val courseId : Long
)