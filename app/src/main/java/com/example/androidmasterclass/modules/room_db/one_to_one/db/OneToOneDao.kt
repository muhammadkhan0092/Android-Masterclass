package com.example.androidmasterclass.modules.room_db.one_to_one.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidmasterclass.modules.room_db.one_to_one.models.DataAddress
import com.example.androidmasterclass.modules.room_db.one_to_one.models.DataUser
import com.example.androidmasterclass.modules.room_db.one_to_one.models.DataUserWithAddress
import kotlinx.coroutines.flow.Flow

@Dao
interface OneToOneDao {
    @Insert
    suspend fun insertDataUser(dataUser: DataUser) : Long

    @Insert
    suspend fun insertDataAddress(dataAddress: DataAddress)

    @Query("Select * FROM DataUser")
    fun getUserWithAddressesFlow() : Flow<List<DataUserWithAddress>>
}