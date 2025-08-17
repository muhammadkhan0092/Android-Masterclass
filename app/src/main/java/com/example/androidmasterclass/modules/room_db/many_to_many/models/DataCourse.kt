package com.example.androidmasterclass.modules.room_db.many_to_many.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DataCourse")
data class DataCourse(
    @PrimaryKey(autoGenerate = true)
    val courseId : Long,
    val courseName : String
)
