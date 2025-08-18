package com.example.androidmasterclass.modules.room_db.one_to_one.domain.models

import androidx.room.Embedded
import androidx.room.Relation

data class DataUserWithAddress(
    @Embedded val user : DataUser,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userOwnerId",
    )
    val address : DataAddress
)
