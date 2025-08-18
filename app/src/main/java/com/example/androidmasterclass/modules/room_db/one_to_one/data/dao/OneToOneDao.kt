package com.example.androidmasterclass.modules.room_db.one_to_one.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidmasterclass.modules.room_db.one_to_one.domain.models.DataAddress
import com.example.androidmasterclass.modules.room_db.one_to_one.domain.models.DataUser
import com.example.androidmasterclass.modules.room_db.one_to_one.domain.models.DataUserWithAddress
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