package com.example.androidmasterclass.modules.room_db.one_to_one.domain.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
@Entity(
    tableName = "DataAddress",
    foreignKeys = [
        ForeignKey(
            entity = DataUser::class,
            parentColumns = ["userId"],
            childColumns = ["userOwnerId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["userOwnerId"], unique = true)]
)
data class DataAddress(
    @PrimaryKey(autoGenerate = true)
    val addressId : Long = 0,
    val city : String,
    val userOwnerId : Long
)
