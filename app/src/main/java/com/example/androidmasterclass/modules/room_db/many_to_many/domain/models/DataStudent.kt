package com.example.androidmasterclass.modules.room_db.many_to_many.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("DataStudent")
data class DataStudent(
    @PrimaryKey(autoGenerate = true)
    val studentId : Long,
    val studentName : String
)
