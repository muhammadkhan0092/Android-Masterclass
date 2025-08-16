package com.example.androidmasterclass.modules.room_db.one_to_one.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "DataUser")
data class DataUser(
    @PrimaryKey(autoGenerate = true)
    val userId : Long = 0,
    val userName : String,
    val userEmail : String
)
